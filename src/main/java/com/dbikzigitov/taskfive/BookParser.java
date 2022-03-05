package com.dbikzigitov.taskfive;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * BookParser class implements methods to parse file and write info about this file in some file.
 *
 */
public class BookParser {
    /**
     * countWordsInFIle make Map with "word" - "count of words" from some file.
     *
     * @param filePath - path of file.
     * @return Map: "word" - "count of words".
     */
    public static Map<String, Long> countWordsInFile(String filePath) throws IOException {

        if (!Files.exists(Paths.get(filePath))) {
            throw new FileNotFoundException("Cant find file, check file path!");
        }
        if (Files.isDirectory(Paths.get(filePath))) {
            throw new IllegalArgumentException("This file is directory, check file path!");
        }

        Map<String, Long> countMap = null;

        try {
            countMap = Files.lines(Paths.get(filePath), Charset.defaultCharset())
                    .flatMap(l -> Stream.of(l.split("[\\p{Punct}\\s]")).filter(p -> p.length() != 0))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countMap;
    }

    /**
     * writeInfoToFile method write "word" - "count of words" if file.
     *
     * @param filePath - file to write.
     * @param countMap - Map: "word" - "count of words".
     */
    public static void writeInfoToFile(String filePath, Map<String, Long> countMap) throws IOException {
        if (Files.exists(Paths.get(filePath))) {
            throw new FileNotFoundException("File exists, cant rewritten, check file path!");
        }
        if (Files.isDirectory(Paths.get(filePath))) {
            throw new IllegalArgumentException("This file is directory, check file path!");
        }

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (String key : countMap.keySet()) {
                fileWriter.write(key + " " + countMap.get(key) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * writeFiles method makes files with name "files names" x "count of words" in file.
     *
     * @param dirPath - directory to make files.
     * @param countMap - Map: "word" - "count of words".
     */
    public static void writeFiles(String dirPath, Map<String, Long> countMap) throws Exception {
        if (!Files.exists(Paths.get(dirPath))) {
            throw new FileNotFoundException("Cant find directory, check path!");
        }
        if (!Files.isDirectory(Paths.get(dirPath))) {
            throw new IllegalArgumentException("This is not a directory, check path!");
        }

        Map<String, CompletableFuture<String>> completableFutureMap = new HashMap<>();
        for (String key : countMap.keySet()) {
            completableFutureMap.put(key, CompletableFuture.supplyAsync(() -> {
                StringBuilder stringBuilder = new StringBuilder();
                for (long i = 0; i < countMap.get(key); i++) {
                    stringBuilder.append(key).append(" ");
                }
                return stringBuilder.toString();
            }));
        }

        for (String key : completableFutureMap.keySet()) {
            try (FileWriter fileWriter = new FileWriter(dirPath + "\\" + key + ".txt")) {
                fileWriter.write(completableFutureMap.get(key).get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Entry point of program, explores "War and Peace" book.
     *
     * @param args - arguments of program.
     */
    public static void main(String[] args) throws Exception {
        String bookPath = ".\\src\\main\\java\\com\\resources\\WarAndPeace.txt";
        String infoPath = ".\\src\\main\\java\\com\\resources\\result.txt";
        String dirPath = ".\\src\\main\\java\\com\\resources\\createdFiles";
        Map<String, Long> cntMap = countWordsInFile(bookPath);
        writeInfoToFile(infoPath, cntMap);
        writeFiles(dirPath, cntMap);
    }
}

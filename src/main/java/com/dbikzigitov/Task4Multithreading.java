package com.dbikzigitov;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Task4Multithreading class implements multithreaded tan(numbersList).
 */
public class Task4Multithreading {
    /**
     * getNumbersFromFile method pulls all numbers from a file.
     *
     * @param nameOfFile - name of file.
     * @return list of numbers.
     */
    public static List<Double> getNumbersFromFile(String nameOfFile) {
        List<Double> numbersList = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get(nameOfFile))) {
            while (scanner.hasNext()) {
                if (scanner.hasNextDouble()) {
                    numbersList.add(scanner.nextDouble());
                } else {
                    scanner.next();
                }
            }
        } catch (IOException e) {
            System.out.println("Cant open/find file with numbers, check name of file.");
        }
        return numbersList;
    }

    /**
     * writeNumbersInFile method write tan(num) = ... in file for all numbers in numberList.
     *
     * @param numbersList - list of numbers.
     * @param tanList - list of tangents.
     * @param fileName - name of file to write.
     */
    public static void writeNumbersInFile(List<Double> numbersList, List<Double> tanList, String fileName) throws IllegalArgumentException, IOException {
        File file = new File(fileName);
        if (file.exists()) {
            throw new IllegalArgumentException("File with this name exists, cant rewritten! Check fileName");

        }
        if (file.createNewFile()) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                for (int i = 0; i < numbersList.size(); i++) {
                    fileWriter.write("tan( " + numbersList.get(i) + " ) = " + tanList.get(i) + "\n");
                }
            }
        }
    }


    /**
     * calculatingTangents method calculating tan for numbers from list.
     *
     * @param numbersList - list of numbers.
     * @param numberOfThreads - number of treads.
     * @return list of tan(numbersList)
     */
    public static List<Double> calculatingTangents(List<Double> numbersList, int numberOfThreads) throws Exception {
        if (numberOfThreads <= 0) {
            throw new Exception("Number of threads must be greater than 0, check this arg.");
        }

        List<Double> tangentsList = new ArrayList<>();
        List<Future<Double>> futureList = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);

        try {
            for (int i = 0; i < numbersList.size(); i++) {
                int finalI = i;
                futureList.add(executor.submit(() -> Math.tan(numbersList.get(finalI))));
            }
        } finally {
            executor.shutdown();
        }

        for (Future<Double> future : futureList) {
            try {
                tangentsList.add(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return tangentsList;
    }
}

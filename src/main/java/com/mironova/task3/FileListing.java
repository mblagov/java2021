package com.mironova.task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileListing class implements writing the list of files in directory to file.
 */
public class FileListing {

    /**
     * This is the main method which makes use of getValues method.
     */
    public static void main(String[] args) throws IOException {
        File startFolder = new File(args[0]);
        String resultFile = new File(args[1]).getAbsolutePath();
        List<String> result = new ArrayList<>();
        verifyDirectory(startFolder);
        verifyFile(new File(resultFile));
        getFileListing(startFolder, result);
        writeToFile(resultFile, result);
    }

    /**
     * GetFileListing method implements getting of the list of files in directory.
     *
     * @param startFolder First folder.
     * @param result List of folders' and files' names.
     *
     */
    public static void getFileListing(File startFolder, List<String> result) {
        File[] files = startFolder.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                getFileListing(f, result);
            }
            if (f.isFile()) {
                result.add(f.getPath().substring(2));
            }
        }
    }

    /**
     * WriteToFile method implements writing the result list to file.
     *
     * @param file File for result.
     * @param result List of folders' and files' names.
     *
     */
    public static void writeToFile(String file, List<String> result) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        for (String s : result) {
            fileWriter.write(s + System.lineSeparator());
        }
        fileWriter.close();
    }


    /**
     * VerifyFile method implements verifying of directory correctness.
     *
     * @param directory Directory for verifying.
     *
     */
    public static void verifyDirectory(File directory) throws FileNotFoundException {
        if (!directory.exists()) {
            throw new FileNotFoundException("Directory does not exist: " + directory);
        }
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Is not a directory: " + directory);
        }
    }

    /**
     * VerifyFile method implements verifying of file name correctness.
     *
     * @param file File for verifying.
     *
     */
    public static void verifyFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("File does not exist: " + file);
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("Is not a file: " + file);
        }
    }
}

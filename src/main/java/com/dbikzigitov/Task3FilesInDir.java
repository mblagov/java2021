package com.dbikzigitov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * task3FilesInDir making a file with the names of folders and files in the specified folder.
 * File and folder for viewing are passed through String [] args.
 */
public class Task3FilesInDir {

    /**
     * task3FilesInDir entry point.
     *
     * @param args - args[0]: string: file/folder to check,
     *             args[1]: string: txt file for recording results
     */
    public static void main(String[] args) throws IOException {
        File dirStart = new File(args[0]);
        checkDir(dirStart);
        List<String> result = getFileTree(args[0], "");
        writeToFile(args[1], result);
    }

    /**
     * Recursive file traversal with paths to the file in dirAndFiles.
     *
     * @param dirName - string: current Folder/file
     * @param currentFile - string: current name of path to dirName File
     * @return List of strings paths
     **/
    public static List<String> getFileTree(String dirName, String currentFile) {

        List<String> dirAndFiles = new ArrayList<>();
        File dir = new File(dirName);
        int flag = 0;

        String currentDirOrFileStr = currentFile + dir.getName() + (!dir.isDirectory() ? "" : "/");

        if (dir.isDirectory()) {
            File[] list = dir.listFiles();
            if (Objects.requireNonNull(list).length != 0) {
                for (File name : list) {
                    dirAndFiles.addAll(getFileTree(name.getPath(), currentDirOrFileStr));
                }
            } else {
                flag = 1;
            }
        } else {
            flag = 1;
        }

        if (flag == 1) {
            dirAndFiles.add(currentDirOrFileStr);
        }
        return dirAndFiles;
    }

    /**
     * Check folder to exists and folder = Directory.
     *
     * @param dir - Directory for check
     */
    public static void checkDir(File dir) throws FileNotFoundException {
        if (!dir.exists()) {
            throw new FileNotFoundException("Can't find start path, check start args !");
        }
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Start path is not a directory, check start args!");
        }
    }

    /**
     * WriteToFile method implements writing the result list to file.
     *
     * @param file - file to write a result
     *
     */
    public static void writeToFile(String file, List<String> result) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (String s : result) {
                fileWriter.write(s + "\n");
            }
        }
    }
}
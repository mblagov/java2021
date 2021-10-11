package com.mblagov.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathApi {

    public static void main(String[] args) throws IOException {
        // Link a physical file with a code
        Path path = Path.of("readme.txt");
        System.out.println(path.toAbsolutePath());

        Path absolutePath = Path.of("/home/vogal/projects/svp_java_2021/java2021/readme.txt");
        System.out.println(absolutePath);

        System.out.println(path.toAbsolutePath());

        //Check existence of the path
        System.out.println(Files.exists(path));

        // Get last modified time of the path
        System.out.println(Files.getLastModifiedTime(path));

        System.out.println(Files.readString(path));

    }
}

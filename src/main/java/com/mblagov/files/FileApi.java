package com.mblagov.files;

import java.io.*;

public class FileApi {

    public static void main(String[] args) {
        // "/home/vogal/projects/svp_java_2021/java2021/readme.txt"
        File f = new File("readme.txt");
        System.out.println(f.exists());
        System.out.println(f.isFile());
        System.out.println(f.isDirectory());

        try (FileInputStream fis = new FileInputStream(f);
             BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {
            System.out.println(br.readLine());
        } catch (IOException e) {
            System.out.println("There was an error reading file " + f.getAbsolutePath());
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(f)) {
//            BufferedWriter
//                    PrintWriter
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

package com.dbikzigitov.taskThreeTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.dbikzigitov.taskthree.Task3FilesInDir;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Task3Test {
    @Test
    public void testGetFileTree() {
        List<String> expResults = new ArrayList<>();
        expResults.add("TestJava/Folder1/1.JPG");
        expResults.add("TestJava/Folder1/2.JPG");
        expResults.add("TestJava/Folder1/3.JPG");
        expResults.add("TestJava/Folder1/4.JPG");
        expResults.add("TestJava/Folder1/5.JPG");
        expResults.add("TestJava/Folder2/2.1.txt");
        expResults.add("TestJava/Folder2/2.2.txt");
        expResults.add("TestJava/Folder2/Folder 2.1/");
        expResults.add("TestJava/Folder3/");
        List<String> checkResults = Task3FilesInDir.getFileTree("D:\\JavaTasks\\JavaTask3\\TestJava", "");
        assertEquals(checkResults, expResults);

    }

    @Test
    public void testVerifyDirectory1() {
        assertThrows(Exception.class, () ->
                Task3FilesInDir.checkDir(new File("D:\\JavaTasks\\JavaTask88\\TestJava")));
    }

    @Test
    public void testVerifyDirectory2() {
        assertThrows(Exception.class, () ->
                Task3FilesInDir.checkDir(new File("D:\\JavaTasks\\JavaTask3\\Results.txt")));
    }

    @Test
    public void testWriteToFile() throws IOException {
        List<String> testList = new ArrayList<>();
        testList.add("1");
        testList.add("2");
        Task3FilesInDir.writeToFile("test.txt", testList);
        BufferedReader br = new BufferedReader(new FileReader("test.txt"));
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        while ((s = br.readLine()) != null) {
            String str = s + System.lineSeparator();
            stringBuilder.append(str);
        }
        String resultStr = stringBuilder.toString();
        br.close();
        assertEquals("1" + System.lineSeparator() + "2" + System.lineSeparator(), resultStr);
    }
}

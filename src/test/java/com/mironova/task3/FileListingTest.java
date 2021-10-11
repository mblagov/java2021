package com.mironova.task3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * FileListing tester.
 */
public class FileListingTest {

    @Test
    public void testGetFileListing() {
        List<String> result = new ArrayList<>();
        FileListing.getFileListing(new File("./resources"), result);
        List<String> expectedResult = new ArrayList<>();
        expectedResult.add("resources\\doc.txt");
        expectedResult.add("resources\\folder1\\doc1.txt");
        expectedResult.add("resources\\folder1\\doc2.txt");
        expectedResult.add("resources\\folder1\\doc3.txt");
        expectedResult.add("resources\\folder1\\folder2\\doc4.txt");
        assertEquals(expectedResult, result);
    }

    @Test
    public void testWriteToFile() throws IOException {
        List<String> testList = new ArrayList<>();
        testList.add("test");
        testList.add("string");
        FileListing.writeToFile("./test.txt", testList);
        BufferedReader br = new BufferedReader(new FileReader("./test.txt"));
        String s;
        StringBuilder stringBuilder = new StringBuilder();
        while ((s = br.readLine()) != null) {
            String str = s + "\n";
            stringBuilder.append(str);
        }
        String resultStr = stringBuilder.toString();
        br.close();
        assertEquals("test\nstring\n", resultStr);
    }

    @Test
    public void testVerifyDirectory1() {
        boolean thrown = false;
        try {
            FileListing.verifyDirectory(new File(""));
        } catch (FileNotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testVerifyDirectory2() {
        boolean thrown = false;
        try {
            FileListing.verifyDirectory(new File("./result.txt"));
        } catch (IllegalArgumentException | FileNotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testVerifyFile1() {
        boolean thrown = false;
        try {
            FileListing.verifyFile(new File(""));
        } catch (FileNotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testVerifyFile2() {
        boolean thrown = false;
        try {
            FileListing.verifyFile(new File("./resources"));
        } catch (IllegalArgumentException | FileNotFoundException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}

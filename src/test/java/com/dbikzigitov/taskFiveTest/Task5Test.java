package com.dbikzigitov.taskFiveTest;

import com.dbikzigitov.taskfive.BookParser;
import com.dbikzigitov.taskthree.Task3FilesInDir;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {
    private final String cntWordPath = ".\\src\\test\\java\\com\\resources\\taskfivetestfiles\\countword.txt";
    private final String dirTestPath = ".\\src\\test\\java\\com\\resources\\taskfivetestfiles\\CreatedFiles";
    private final String notExFile = ".\\src\\test\\java\\com\\resources\\taskfivetestfiles\\notex.txt";

    public Map<String, Long> generateMap() {
        Map<String, Long> testMap = new HashMap<>();
        testMap.put("foo", (long)3);
        testMap.put("bar", (long)2);
        testMap.put("baz", (long)1);
        return testMap;
    }

    @Test
    public void testCountWordInFile() throws IOException {
        Map<String, Long> actMap = BookParser.countWordsInFile(cntWordPath);
        var expMap = generateMap();
        assertEquals(expMap, actMap);
    }

    @Test
    public void testWriteFiles() throws Exception {
        var expMap = generateMap();
        BookParser.writeFiles(dirTestPath, expMap);
        List<String> actFiles = Task3FilesInDir.getFileTree(dirTestPath, "");
        List<String> expFiles = new ArrayList<>();
        expFiles.add("CreatedFiles/bar.txt");
        expFiles.add("CreatedFiles/baz.txt");
        expFiles.add("CreatedFiles/foo.txt");
        assertEquals(actFiles, expFiles);
    }

    @Test
    public void testExceptionOne() {
        assertThrows(FileNotFoundException.class, () -> BookParser.countWordsInFile(notExFile));
    }

    @Test
    public void testExceptionTwo() {
        assertThrows(IllegalArgumentException.class, () -> BookParser.countWordsInFile(".\\src\\test\\java\\com\\resources\\taskfivetestfiles\\"));
    }

    @Test
    public void testExceptionThree() {
        assertThrows(FileNotFoundException.class, () -> BookParser.writeFiles(".\\src\\test\\java\\com\\resources\\taskfivetestfiles1\\", generateMap()));
    }

    @Test
    public void testExceptionFour() {
        assertThrows(IllegalArgumentException.class, () -> BookParser.writeFiles(cntWordPath, generateMap()));
    }

    @Test
    public void testExceptionFive() {
        assertThrows(FileNotFoundException.class, () -> BookParser.writeInfoToFile(cntWordPath, generateMap()));
    }
}

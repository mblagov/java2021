package com.dbikzigitov.taskFourTest;

import com.dbikzigitov.taskfour.Task4Multithreading;
import org.junit.jupiter.api.Test;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class Task4Test {
    private final String numberPath = ".\\src\\test\\java\\com\\resources\\numbers.txt";
    private final String tangentsPath = ".\\src\\test\\java\\com\\resources\\tangents.txt";

    public List<Double> generateNumbers(int numOfNumbers) {
        try (FileWriter fileWriter = new FileWriter(numberPath)) {
            for (int i = 0; i < numOfNumbers; i++) {
                fileWriter.write(i + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Double> numbersList;
        numbersList = Task4Multithreading.getNumbersFromFile(numberPath);
        return numbersList;
    }

    @Test
    public void testGetNumbersFromFile() {
        List<Double> goodNumbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            goodNumbers.add((double) i);
        }
        assertEquals(generateNumbers(5), goodNumbers);
    }

    @Test
    public void testCalculatingTangents() throws Exception {
        List<Double> goodTan = new ArrayList<>();
        List<Double> goodNumbers = generateNumbers(5);
        for (int i = 0; i < 5; i++) {
            goodTan.add(Math.tan(i));
        }
        List<Double> testTan = Task4Multithreading.calculatingTangents(goodNumbers, 5);
        assertEquals(testTan, goodTan);
    }

    @Test
    public void testCalculatingTangentsEx() {
        List<Double> numList = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            numList.add((double) i);
        }
        assertThrows(Exception.class, () -> Task4Multithreading.calculatingTangents(numList, -2));
    }

    @Test
    public void testWriteNumbersInFile() {
        List<Double> numList = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            numList.add((double) i);
        }
        assertThrows(IllegalArgumentException.class, () -> Task4Multithreading.writeNumbersInFile(numList, numList, tangentsPath));
    }

    @Test
    public void test1() throws Exception {
        List<Double> numList = generateNumbers(100);
        long start1, end1, start2, end2;

        start1 = System.currentTimeMillis();
        Task4Multithreading.calculatingTangents(numList, 1);
        end1 = System.currentTimeMillis();

        start2 = System.currentTimeMillis();
        Task4Multithreading.calculatingTangents(numList, 10);
        end2 = System.currentTimeMillis();

        System.out.println("In single thread for 100 numbers: " + (end1 - start1) + " ms");
        System.out.println("In multi thread for 100 numbers: " + (end2 - start2) + " ms");
    }

    @Test
    public void test2() throws Exception {
        List<Double> numList = generateNumbers(100000);
        long start1, end1, start2, end2;

        start1 = System.currentTimeMillis();
        Task4Multithreading.calculatingTangents(numList, 1);
        end1 = System.currentTimeMillis();

        start2 = System.currentTimeMillis();
        Task4Multithreading.calculatingTangents(numList, 10);
        end2 = System.currentTimeMillis();


        System.out.println("In single thread for 100000 numbers: " + (end1 - start1) + " ms");
        System.out.println("In multi thread for 100000 numbers: " + (end2 - start2) + " ms");
    }

    @Test
    public void test3() throws Exception {
        List<Double> numList = generateNumbers(2000000);
        long start1, end1, start2, end2;

        start1 = System.currentTimeMillis();
        Task4Multithreading.calculatingTangents(numList, 1);
        end1 = System.currentTimeMillis();

        start2 = System.currentTimeMillis();
        Task4Multithreading.calculatingTangents(numList, 10);
        end2 = System.currentTimeMillis();

        System.out.println("In single thread for 2000000 numbers: " + (end1 - start1) + " ms");
        System.out.println("In multi thread for 2000000 numbers: " + (end2 - start2) + " ms");
    }
}

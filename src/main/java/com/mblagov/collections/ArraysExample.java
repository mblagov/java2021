package com.mblagov.collections;

import java.util.Arrays;
import java.util.List;

public class ArraysExample {

    public static void main(String[] args) {
        // Create from items
        int[] numbers = {167, -2, 16, 99, 26, 92, 43, -234, 35, 80};

        // Create with length
        int [] numbersCopy = new int[10];

        // Convert to a list
        List<int[]> ints = Arrays.asList(numbers);

        // In-place sort
        Arrays.sort(numbers);
        Arrays.stream(numbers).forEach(System.out::println);
        System.out.println(Arrays.toString(numbers));

        // Binary search
        int index = Arrays.binarySearch(numbers, 16);
        System.out.println(index);

        // Binary search
        int notExistingIndex = Arrays.binarySearch(numbers, 98);
        System.out.println(notExistingIndex);

        // Make a copy
        int[] newInts = Arrays.copyOf(numbers, 10);
    }
}

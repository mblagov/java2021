package com;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class RegexpTest {

    public static void main(String[] args) {
        System.out.println("123 123".matches("\\d+"));


        InputStream in = System.in;

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("123 \n 213".getBytes());
        BufferedReader input = new BufferedReader(new InputStreamReader(byteArrayInputStream));
        input.lines().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        scanner.hasNextLong();

    }
}

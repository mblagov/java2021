package com.mivaschenko.firstproblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class for solving classical ATM problem.
 */
public class AutomatedTellerMachine {
    private long target;
    private long[] nominal;

    /**
     * Input processing.
     */
    public void inputValues(InputStream in) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            System.out.print("Input amount: ");
            String inputStr = input.readLine().trim();
            if (inputStr.isEmpty()) {
                throw new IOException("Input cannot be empty!");
            }
            if (!inputStr.matches("\\d+")) {
                throw new IOException("Invalid value! Value must be a number");
            }
            long[] targetValStr = Arrays.stream(inputStr.split("\\s+"))
                    .mapToLong(Long::parseLong).toArray();
            this.target = targetValStr[0];
            if (target <= 0) {
                throw new IOException("Target value must be positive");
            }

            System.out.print("Input values: ");
            String valuesStr = input.readLine().trim();
            if (valuesStr.isEmpty()) {
                throw new IOException("Input cannot be empty!");
            }
            if ((!valuesStr.replaceAll("\\s+", "").matches("\\d+"))) {
                throw new IOException("Invalid value(s)! There can only be numbers");
            }
            long[] temp = Arrays.stream(valuesStr.split("\\s+"))
                    .mapToLong(Long::parseLong).toArray();
            Arrays.sort(temp);
            List<Long> nominalList = new ArrayList<>();
            for (int i=0; i<temp.length; i++) {
                if (temp[i] <= 0) {
                    throw new IOException("Invalid value! "
                            + "Numbers must be integer and greater than zero!");
                }
                if (i==0 || temp[i]!=temp[i-1]) {
                    nominalList.add(temp[i]);
                }
            }
            this.nominal = nominalList.stream().mapToLong(i -> i).toArray();
            System.out.println(Arrays.toString(nominal));
        } catch (IOException e) {
            System.out.println("Input error!");
            e.printStackTrace();
        }
    }

    /**
     * Makes new combinations from combinations of prev.
     */
    public Set<List<Long>> addValues(Set<List<Long>> prev, long[] values) {
        Set<List<Long>> newSet = new HashSet<>();
        for (List<Long> previousArray : prev) {
            List<Long> newValue = new ArrayList<>();
            for (int j = 0; j < values.length; j++) {
                if (newValue.size() <= j) {
                    newValue.add(j, previousArray.get(j) + values[j]);
                } else {
                    newValue.set(j, previousArray.get(j) + values[j]);
                }
            }
            newSet.add(newValue);
        }
        return newSet;
    }

    /**
     *Creates main structure for solving.
     * Fills it with combinations
     * Returns all combinations for target value
     * (that is the last element)
     */
    public Set<List<Long>> solve() {
        ArrayList<Set<List<Long>>> result = new ArrayList<>();
        var emp = new long[nominal.length];
        var zeros = new Long[nominal.length];
        Arrays.fill(zeros, (long) 0);
        List<Long> fi = Arrays.asList(zeros);
        result.add(Stream.of(fi).collect(Collectors.toSet()));
        for (int i = 1; i <= target; ++i) {
            result.add(new HashSet<>());
            for (int j = 0; j < nominal.length; ++j) {
                if (i - nominal[j] >= 0) {
                    emp[j] = 1;
                    Set<List<Long>> inter = addValues(result.get((int) (i - nominal[j])), emp);
                    for (List<Long> s : inter) {
                        result.get(i).add(s);
                    }
                    emp[j] = 0;
                }
            }
            if (result.size() > nominal[nominal.length - 1] + 1) {
                result.set((int) (result.size() - nominal[nominal.length - 1] - 1), null);
            }
        }
        return result.get(result.size() - 1);
    }

    /**
     * Main method.
     */
    public static void main(String[] args) {
        AutomatedTellerMachine test = new AutomatedTellerMachine();
        test.inputValues(System.in);
        var res = test.solve();
        System.out.println("=============");
        System.out.println("Combinations: " + res.size());
        for (List<Long> s : res) {
            System.out.println(Arrays.toString(s.toArray()));
        }
    }
}

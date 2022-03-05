package com.mivaschenko.firstproblem;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ATM {
    private long target;
    private long[] nominals;

    public void inputVals(InputStream in) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(in));

            System.out.print("Input amount: ");
            String inputStr = input.readLine().trim();
            if (inputStr.isEmpty())
                throw new IOException("Input cannot be empty!");
            if (!inputStr.matches("\\d+")) {
                throw new IOException("Invalid value! Value must be a number");
            }
            long[] targetValStr = Arrays.stream(inputStr.split("\\s+")).mapToLong(Long::parseLong).toArray();
            this.target = targetValStr[0];
            if (target <= 0) {
                throw new IOException("Target value must be positive");
            }

            System.out.print("Input values: ");
            String valuesStr = input.readLine().trim();
            if (valuesStr.isEmpty())
                throw new IOException("Input cannot be empty!");
            if ((!valuesStr.replaceAll("\\s+", "").matches("\\d+"))) {
                throw new IOException("Invalid value(s)! There can only be numbers");
            }
            this.nominals = Arrays.stream(valuesStr.split("\\s+")).mapToLong(Long::parseLong).toArray();

            Arrays.sort(nominals);
            for (long s : nominals) {
                if (s <= 0) {
                    throw new IOException("Invalid value! Numbers must be integer and greater than zero!");
                }
            }
            System.out.println(Arrays.toString(nominals));
        } catch (IOException e) {
            System.out.println("Input error!");
            e.printStackTrace();
        }
    }

    public Set<List<Long>> addVals(Set<List<Long>> prev, long[] vals) {
        List<Long> newval;
        Set<List<Long>> newset = new HashSet<>();
        for (List<Long> prevarr : prev) {
            newval = new ArrayList<>();
            for (int j = 0; j < vals.length; j++) {
                if (newval.size() <= j)
                    newval.add(j, prevarr.get(j) + vals[j]);
                else
                    newval.set(j, prevarr.get(j) + vals[j]);
            }
            newset.add(newval);
        }
        return newset;
    }

    public Set<List<Long>> solve() {
        ArrayList<Set<List<Long>>> result = new ArrayList<>();
        var emp = new long[nominals.length];
        var zeros = new Long[nominals.length];
        Arrays.fill(zeros, (long) 0);
        List<Long> fi = Arrays.asList(zeros);
        result.add(Stream.of(fi).collect(Collectors.toSet()));
        for (int i = 1; i <= target; ++i) {
            result.add(new HashSet<>());
            for (int j = 0; j < nominals.length; ++j) {
                if (i - nominals[j] >= 0) {
                    emp[j] = 1;
                    Set<List<Long>> inter = addVals(result.get((int) (i - nominals[j])), emp);
                    for (List<Long> s : inter) {
                        result.get(i).add(s);
                    }
                    emp[j] = 0;
                }
            }
            if (result.size() > nominals[nominals.length - 1] + 1)
                result.set((int) (result.size() - nominals[nominals.length - 1] - 1), null);
        }
        return result.get(result.size() - 1);
    }

    public static void main(String[] args) {
        ATM test = new ATM();
        test.inputVals(System.in);
        var res = test.solve();
        System.out.println("=============");
        System.out.println("Combinations: " + res.size());
        for (List<Long> s : res) {
            System.out.println(Arrays.toString(s.toArray()));
        }
    }
}

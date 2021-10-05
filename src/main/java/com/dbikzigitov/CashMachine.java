package com.dbikzigitov;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Scanner;


/**
 * CashMachine class showing the splitting of a number into the sum of values.
 */
public class CashMachine {
    /**
     * CashMachine entry point.
     */
    public static void main(String[] args) {
        String valueStr;
        String amountStr;
        long amount;
        long[] values = new long[0];

        try (Scanner scan = new Scanner(System.in)) {

            System.out.print("Input amount: ");
            amountStr = scan.nextLine();

            System.out.print("Input values: ");
            valueStr = scan.nextLine();
        }
        try {
            amount = Long.parseLong(amountStr);
        } catch (NumberFormatException e) {
            System.out.println("Exception: the entered amount is not a number");
            return;
        }
        
        try {
            values = Arrays.stream(valueStr.split(" ")).mapToLong(Long::parseLong).toArray();
        } catch (NumberFormatException e) {
            System.out.println("Exception: some entered values is not a number");
        }
        
        var variantsOfExchange = amountExchange(values, amount);
        long numberOfComb = printVars(variantsOfExchange);
        System.out.printf("Number of combinations: %d", numberOfComb);
    }

    /**
     * Cashier class displays variants for splitting a number into the sum of values.
     *
     * @param values array of input values
     * @param amount number to split
     * @return List of lists splitting variants
     */
    public static ArrayList<ArrayList<Long>> amountExchange(long[] values, long amount) {
        try {

            for (long value : values) {
                if (value <= 0) {
                    throw new IOException("some input values less than 0");
                }
            }

            if (amount <= 0) {
                throw new IOException("input amount less than 0");
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
        ArrayList<ArrayList<Long>> vars = new ArrayList<>();
        ArrayList<ArrayList<Long>> goodVars = new ArrayList<>();

        for (long value : values) {
            ArrayList<Long> tempList = new ArrayList<>();
            tempList.add(value);
            vars.add(tempList);
        }

        for (ArrayList<Long> integers : vars) {
            if (Objects.equals(integers.get(0), amount)) {
                goodVars.add(integers);
            }
        }

        while (vars.size() > 0) {

            ArrayList<ArrayList<Long>> tempVars = new ArrayList<>();

            for (ArrayList<Long> var : vars) {
                tempVars.addAll(addCash(values, var));
            }


            vars.clear();

            for (ArrayList<Long> var : tempVars) {
                Collections.sort(var);
            }

            var set = new LinkedHashSet<>(tempVars);
            tempVars.clear();
            tempVars.addAll(set);

            for (ArrayList<Long> tempVar : tempVars) {
                long sum = 0;
                for (Long integer : tempVar) {
                    sum += integer;
                }
                if (sum < amount) {
                    vars.add(tempVar);
                }
                if (sum == amount) {
                    goodVars.add(tempVar);
                }
            }
        }

        for (ArrayList<Long> goodVar : goodVars) {
            Collections.sort(goodVar);
        }

        var set = new LinkedHashSet<>(goodVars);
        goodVars.clear();
        goodVars.addAll(set);

        return goodVars;
    }

    /**
     * Add_cash class add values to variants in list.
     *
     * @param values - array of input values
     * @param list - list of variants
     * @return list of updated variants
     */
    public static ArrayList<ArrayList<Long>> addCash(long[] values, ArrayList<Long> list) {
        ArrayList<ArrayList<Long>> listAddCash = new ArrayList<>();
        for (long value : values) {
            ArrayList<Long> temp = new ArrayList<>(list);
            temp.add(value);
            listAddCash.add(temp);
        }
        return listAddCash;
    }

    /**
     * printVars method to print variants and return number of combinations.
     *
     * @param variants - list of lists variants
     * @return number of variants
     */
    public static long printVars(ArrayList<ArrayList<Long>> variants) {
        System.out.println("Number of combinations: " + variants.size());
        for (ArrayList<Long> var : variants) {
            for (Long integer : var) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
        return variants.size();
    }
}

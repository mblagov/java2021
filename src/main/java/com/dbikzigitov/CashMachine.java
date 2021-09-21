package com.dbikzigitov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * CashMachine class showing the splitting of a number into the sum of values.
 */
public class CashMachine {
    /**
     * CashMachine entry point.
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Input values: ");
        String valueStr = scan.nextLine();

        System.out.print("Input amount: ");
        int amount = scan.nextInt();

        int[] values = Arrays.stream(valueStr.split(" ")).mapToInt(Integer::parseInt).toArray();

        var vars = cashier(values, amount);
        System.out.println("Number of combinations: " + vars.size());
        System.out.println("Combinations: ");
        for (ArrayList<Integer> var : vars) {
            for (Integer integer : var) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }

        scan.close();
    }

    /**
     * Cashier class displays variants for splitting a number into the sum of values.
     *
     * @param values array of input values
     * @param amount number to split
     * @return List of lists splitting variants
     */
    public static ArrayList<ArrayList<Integer>> cashier(int[] values, int amount) {
        ArrayList<ArrayList<Integer>> vars = new ArrayList<>();
        ArrayList<ArrayList<Integer>> goodVars = new ArrayList<>();

        for (int value : values) {
            ArrayList<Integer> tempList = new ArrayList<>();
            tempList.add(value);
            vars.add(tempList);
        }

        for (ArrayList<Integer> integers : vars) {
            if (integers.get(0) == amount) {
                goodVars.add(integers);
            }
        }

        while (vars.size() > 0) {

            ArrayList<ArrayList<Integer>> tempVars = new ArrayList<>();

            for (ArrayList<Integer> var : vars) {
                tempVars.addAll(add_cash(values, var));
            }


            vars.clear();

            for (ArrayList<Integer> var : tempVars) {
                Collections.sort(var);
            }

            var set = new LinkedHashSet<>(tempVars);
            tempVars.clear();
            tempVars.addAll(set);

            for (ArrayList<Integer> tempVar : tempVars) {
                int sum = 0;
                for (Integer integer : tempVar) {
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

        for (ArrayList<Integer> goodVar : goodVars) {
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
    public static ArrayList<ArrayList<Integer>> add_cash(int[] values, ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> listAddCash = new ArrayList<>();
        for (int value : values) {
            ArrayList<Integer> temp = new ArrayList<>(list);
            temp.add(value);
            listAddCash.add(temp);
        }
        return listAddCash;
    }
}

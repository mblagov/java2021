package com.dbikzigitov;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



/**
 * CashMachine tester.
 */
public class CashMachineTest {

    @Test
    public void testCashMachineFirstVar() {
        int amount = 5;
        int[] vars = {2, 3};
        ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
        ArrayList<Integer> var1 = new ArrayList<>();
        var1.add(2);
        var1.add(3);
        comb.add(var1);
        var combTest = CashMachine.cashier(vars, amount);
        assertEquals(comb.size(), combTest.size());
        assertEquals(comb, combTest);
    }

    @Test
    public void testCashMachineSecondVar() {
        int amount = 4;
        int[] vars = {1, 2};
        ArrayList<ArrayList<Integer>> comb = new ArrayList<>();
        ArrayList<Integer> var1 = new ArrayList<>();
        ArrayList<Integer> var2 = new ArrayList<>();
        ArrayList<Integer> var3 = new ArrayList<>();
        var1.add(2);
        var1.add(2);

        var2.add(1);
        var2.add(1);
        var2.add(2);

        var3.add(1);
        var3.add(1);
        var3.add(1);
        var3.add(1);
        comb.add(var1);
        comb.add(var2);
        comb.add(var3);
        var combTest = CashMachine.cashier(vars, amount);
        assertEquals(comb.size(), combTest.size());
        assertEquals(comb, combTest);
    }

}

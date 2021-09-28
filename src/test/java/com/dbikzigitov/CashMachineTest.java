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
        long amount = 5L;
        long[] vars = {2L, 3L};
        ArrayList<ArrayList<Long>> comb = new ArrayList<>();
        ArrayList<Long> var1 = new ArrayList<>();
        var1.add(2L);
        var1.add(3L);
        comb.add(var1);
        var combTest = CashMachine.amountExchange(vars, amount);
        assertEquals(comb.size(), combTest.size());
        assertEquals(comb, combTest);
    }

    @Test
    public void testCashMachineSecondVar() {
        long amount = 4L;
        long[] vars = {1L, 2L};
        ArrayList<ArrayList<Long>> comb = new ArrayList<>();
        ArrayList<Long> var1 = new ArrayList<>();
        ArrayList<Long> var2 = new ArrayList<>();
        ArrayList<Long> var3 = new ArrayList<>();
        var1.add(2L);
        var1.add(2L);

        var2.add(1L);
        var2.add(1L);
        var2.add(2L);

        var3.add(1L);
        var3.add(1L);
        var3.add(1L);
        var3.add(1L);
        comb.add(var1);
        comb.add(var2);
        comb.add(var3);
        var combTest = CashMachine.amountExchange(vars, amount);
        assertEquals(comb.size(), combTest.size());
        assertEquals(comb, combTest);
    }

    @Test
    public void testAddCash() {
        ArrayList<ArrayList<Long>> comb = new ArrayList<>();
        ArrayList<Long> test_comb = new ArrayList<>();
        test_comb.add(2L);
        long[] values = {1L, 2L};
        ArrayList<Long> var1 = new ArrayList<>();
        ArrayList<Long> var2 = new ArrayList<>();
        var1.add(2L);
        var1.add(1L);
        var2.add(2L);
        var2.add(2L);
        comb.add(var1);
        comb.add(var2);
        assertEquals(CashMachine.addCash(values, test_comb), comb);

    }

}

package com.mivaschenko.firstproblem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Testing the cases from:
 * https://docs.google.com/spreadsheets/d/1NL0kMdMNkMQ9ujZwR-SY7P2TItLCbPjsjZtdYboblvU/edit#gid=0
 * For example:
 * Target = 5
 * Coins = 3, 2
 * The result should be [1, 1]
 * (One coin of each type)
 */
public class AutomatedTellerMachineTest {
    private Set<ArrayList<Long>> actual;
    private AutomatedTellerMachine test;

    public void setActual(String valuesString) {
        long[] values = Arrays.stream(valuesString.split("\\s+"))
                .mapToLong(Long::parseLong).toArray();
        ArrayList<Long> actualSubList = new ArrayList<>();
        for (long val : values) {
            actualSubList.add(val);
        }
        actual.add(actualSubList);
    }

    public void setInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        test.inputValues(in);
    }

    @BeforeEach
    public void init() {
        test = new AutomatedTellerMachine();
        actual = new HashSet<>();
    }

    @Test
    public void input1Test() {
        setInput("5\n3 2\n");
        setActual("1 1");
        assertEquals(test.solve(), actual);
    }

    @Test
    public void input2Test() {
        setInput("4\n2 1\n");
        setActual("0 2");
        setActual("2 1");
        setActual("4 0");
        assertEquals(test.solve(), actual);
    }

    @Test
    public void input3Test() {
        setInput("4\n1 2\n");
        setActual("0 2");
        setActual("2 1");
        setActual("4 0");
        assertEquals(test.solve(), actual);
    }

    @Test
    public void input4Test() {
        setInput("1000\n1\n");
        setActual("1000");
        assertEquals(test.solve(), actual);
    }

    @Test
    public void input5Test() {
        setInput("1000\n500 1\n");
        setActual("500 1");
        setActual("1000 0");
        setActual("0 2");
        assertEquals(test.solve(), actual);
    }

    @Test
    public void input6Test() {
        setInput("5\n10 6\n");
        assertEquals(test.solve(), actual); //actual пустой, т.к. комбинаций нет
    }

    @Test
    public void input7Test() {
        setInput("5\n1 1\n");
        setActual("5");
        assertEquals(test.solve(), actual);
    }

    @Test
    public void input8Test() {
        try {
            setInput("\n2 1\n");
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void input9Test() {
        try {
            setInput("5\n\n");
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void input10Test() {
        try {
            setInput("0\n2 1\n");
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void input11Test() {
        try {
            setInput("5\n0\n");
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void input12Test() {
        try {
            setInput("-5\n1 2\n");
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void input13Test() {
        try {
            setInput("5\n-1 1\n");
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void input14Test() {
        try {
            setInput("asd\n2 1\n");
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void input15Test() {
        try {
            setInput("5\na b\n");
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    public void input16Test() {
        try {
            setInput("3+2\n1+1 2+1\n");
        } catch (Exception e) {
            fail(e);
        }
    }
}

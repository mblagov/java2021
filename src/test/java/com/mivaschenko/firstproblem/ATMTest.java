package com.mivaschenko.firstproblem;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMTest {

    @Test
    public void solveTest() {
        ATM test = new ATM();

        ByteArrayInputStream in = new ByteArrayInputStream("5\n 3 2\n".getBytes());
        test.inputVals(in);
        var expected = test.solve();

        ArrayList<Long> actualSubList = new ArrayList<>();
        actualSubList.add(1L);
        actualSubList.add(1L);
        Set<ArrayList<Long>> actual = new HashSet<>();
        actual.add(actualSubList);

        assertEquals(expected, actual);
    }
}

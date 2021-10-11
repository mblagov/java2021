package com.mblagov.exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {

    @Test
    public void testExcetionHappened() {
        SomeClassToBeTested someClassToBeTested = new SomeClassToBeTested();
        Assertions.assertThrows(RuntimeException.class, someClassToBeTested::methodThatThrowsAnException);
    }

}

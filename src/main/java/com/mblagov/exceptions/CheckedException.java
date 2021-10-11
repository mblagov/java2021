package com.mblagov.exceptions;

public class CheckedException {

    static class MyException extends Exception {
        public MyException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) throws MyException {
        throw new MyException("This is my exception");
    }
}

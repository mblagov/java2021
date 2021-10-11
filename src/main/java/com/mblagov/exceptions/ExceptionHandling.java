package com.mblagov.exceptions;

import java.util.Random;

public class ExceptionHandling {

    static class InputException extends RuntimeException {

        // Override constructor with the message and cause
        public InputException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static void main(String[] args) {
        try {
            Long.parseLong("asd");
        } catch (Exception e) {
            System.out.println("oops");
        }

        Random r = new Random(System.currentTimeMillis());
        var condition = r.nextBoolean();
        try {
            if (condition) {
                // NumberFormatException
                Long.parseLong("asdf");
            } else {
                throw new Exception("Condition was not true");
            }
        } catch (NumberFormatException e) {
            System.out.println("There was a NumberFormatException");
        } catch (Exception e) {
            System.out.println("There is some exception");
            // printing exception trace
            e.printStackTrace();
        }

        try {
            try {
                // This line throws NumberFormatException
                Long.parseLong("asd");
            } catch (NumberFormatException e) {
                // I'll wrap it with another exception I created above
                throw new InputException("You input was not correct", e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // What if I need to do something anyway
        // Let's say I want to clean-up some variable to get further running code correct
        int i = 0;
        try {
            while (i < 10) {
                System.out.println(i);
                if (i == 5) {
                    throw new Exception("smth happened");
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            i = 0;
        }
    }
}

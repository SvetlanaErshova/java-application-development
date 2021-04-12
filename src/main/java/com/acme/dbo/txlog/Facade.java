package com.acme.dbo.txlog;

import javax.print.DocFlavor;
import java.sql.SQLOutput;
import java.util.Arrays;

import static java.lang.System.*;

public class Facade {
    public static final String PRIMITIVE_PREFIX = "primitive: ";
    public static final String STRING_PREFIX = "string: ";
    public static final String REFERENCE_PREFIX = "reference: ";
    public static final String CHAR_PREFIX = "char: ";
    public static final String ARRAY_PREFIX = "primitives array: ";
    public static final String MATRIX_PREFIX = "primitives matrix: ";

    public static long intAccumulator = 0;
    public static byte byteAccumulator;
    public static String stringAccumulator;
    public static int stringCounter;

    public static void log(int message) {
        stringAccumulator = null;
        stringCounter = 0;
        long longMessage = message + intAccumulator;
        if (longMessage > Integer.MAX_VALUE) {
            print(PRIMITIVE_PREFIX + message);
            flash();
        } else {
            intAccumulator += message;
            print(PRIMITIVE_PREFIX + intAccumulator);
        }
    }


    public static void log(byte message) {
        long longByte = message + byteAccumulator;
        if (longByte > Byte.MAX_VALUE) {
            print(PRIMITIVE_PREFIX + Byte.MAX_VALUE);
            flash();
        } else {
            byteAccumulator = (byte) (byteAccumulator + message);
            print(PRIMITIVE_PREFIX + byteAccumulator);
        }
    }

    public static void log(char message) {
        print(CHAR_PREFIX + message);
    }

    public static void log(String... message) {
        for (String arr : message) {
            print(STRING_PREFIX + arr);
        }
    }

    public static void log(String message) {
        intAccumulator = 0;
        byteAccumulator = 0;
        if (stringAccumulator == null) {
            stringAccumulator = message;
            print(STRING_PREFIX + message);
        } else {
            stringAccumulator = message;
            stringCounter++;
            print(STRING_PREFIX + message + " (x" + stringCounter + ")");
        }
    }

    public static void log(Object message) {
        print(REFERENCE_PREFIX + message);
    }

    public static void log(boolean message) {
        print(PRIMITIVE_PREFIX + message);
    }

    public static void log(int... message) {
        StringBuilder stringArray = new StringBuilder("{");
        for (int i = 0; i < message.length; i++) {
            stringArray.append(message[i]);
            if (i < message.length - 1) {
                stringArray.append(", ");
            }
        }
        stringArray.append("}");
        print(ARRAY_PREFIX + stringArray);
    }

    public static void log(int[][] message) {
        StringBuilder stringArray = new StringBuilder("{" + lineSeparator());
        for (int[] matrix : message) {
            stringArray.append(message + lineSeparator());
        }
        for (int i = 0; i < message.length; i++) {
            stringArray.append(message[i] + lineSeparator());
            if (i < message.length - 1) {
                stringArray.append(lineSeparator());
            }
        }
        stringArray.append("}");
        //       list.stream().collect(Collectors.joining(“,”))

        print(MATRIX_PREFIX + stringArray);
    }

    private static void print(String decorate) {
        out.println(decorate);
    }

    public static void flash() {
        stringAccumulator = null;
        stringCounter = 0;
        intAccumulator = 0;
        byteAccumulator = 0;
    }


}

package com.acme.dbo.txlog;

import javax.print.DocFlavor;
import java.sql.SQLOutput;

public class Facade {
    public static final String PRIMITIVE_PREFIX = "primitive: ";
    public static final String STRING_PREFIX = "string: ";
    public static final String REFERENCE_PREFIX = "reference: ";
    public static final String CHAR_PREFIX = "char: ";

    public static Integer intAccumulator = 0;
    public static byte byteAccumulator;
    public static String stringAccumulator;
    public static int stringCounter;

    public static void log(int message) {
        stringAccumulator = null;
        stringCounter = 0;
        long longMessage = message + intAccumulator.longValue();
        if (longMessage > Integer.MAX_VALUE) {
            print(PRIMITIVE_PREFIX + Integer.MAX_VALUE);
        } else {
            intAccumulator += message;
            print(PRIMITIVE_PREFIX + intAccumulator);
        }
    }

    public static void log(byte message) {
        long longByte = message + byteAccumulator;
        if (longByte > Byte.MAX_VALUE) {
            print(PRIMITIVE_PREFIX + Byte.MAX_VALUE);
        } else {
            byteAccumulator = (byte) (byteAccumulator + message);
            print(PRIMITIVE_PREFIX + byteAccumulator);
        }
    }

    public static void log(char message) {
        print(CHAR_PREFIX + message);
    }

    public static void log(String message) {
        intAccumulator = 0;
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

    private static void print(String decorate) {
        System.out.println(decorate);
    }

    public static void flash() {
        stringAccumulator = null;
        stringCounter = 0;
        intAccumulator = 0;
        byteAccumulator = 0;
    }


}

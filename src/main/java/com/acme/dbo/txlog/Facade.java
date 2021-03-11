package com.acme.dbo.txlog;

public class Facade {
    public static final String PRIMITIVE_PREFIX = "primitive: ";
    public static final String STRING_PREFIX = "string: ";
    public static final String REFERENCE_PREFIX = "reference: ";
    public static final String CHAR_PREFIX = "char: ";

    public static void log(int message) {
        print(decorate(PRIMITIVE_PREFIX, message));
    }

       public static void log(byte message) {
        print(decorate(PRIMITIVE_PREFIX, message));
            }

    public static void log(char message) {
        print(decorate(CHAR_PREFIX, message));
    }

    public static void log(String message) {
        print(decorate(STRING_PREFIX,message));
         }

    public static void log(Object message) {
        print(decorate(REFERENCE_PREFIX, message));
    }

    public static void log(boolean message) {
        print(decorate(PRIMITIVE_PREFIX, message));
    }

    public static void print(Object message) {
        System.out.println(message); }

    private static String decorate(String primitivePrefix, Object message)
    {
        return primitivePrefix + message;
    }
}

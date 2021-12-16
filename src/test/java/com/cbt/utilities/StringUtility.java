package com.cbt.utilities;

public class StringUtility {

    public static String verifyEquals(String expected, String actual){

        return actual.equals(expected) ? "PASS": "FAIL";
    }
}

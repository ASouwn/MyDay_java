package com.example.myday.DB;

public class DBVersion_and_Name {
    private static String NAME="myDates.db";
    private static int version=1;

    public static String getNAME() {
        return NAME;
    }

    public static int getVersion() {
        return version;
    }
}

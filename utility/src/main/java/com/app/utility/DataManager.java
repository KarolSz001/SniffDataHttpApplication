package com.app.utility;
import java.util.Scanner;

public class DataManager {

    private DataManager() {}
    private final static Scanner sc = new Scanner(System.in);

    public static String getLine(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public void close() {
        if (sc != null) {
            sc.close();
        }
    }

}

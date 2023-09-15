package com.MahadevanRDJ.carparkingmanagement.util;

import java.util.Scanner;

public class Validate {
    private static Scanner scanner = new Scanner(System.in);
    private static int integer;

    public static boolean check(Integer value) {
        if (value.getClass().getName().equals("I"))
            return false;
        return true;
    }

    public static boolean check(String value) {
        value = value.toLowerCase();
        for (char c : value.toCharArray())
            if (c < 'a' || c > 'z')
                return false;
        return true;
    }

    public static boolean verifyPassWord(String passWord) {
        if (passWord.length() < 8)
            return false;
        return true;
    }

    public static int getInt() {
        try {
            integer = scanner.nextInt();
            if (!check(integer))
                getInt();
            return integer;
        } catch (Exception e) {
            scanner.nextLine();
            integer = scanner.nextInt();
        }
        return integer;
    }

    public static String getString() {
        String string = scanner.next();
        return string;
    }

    public static void main(String[] args) {
        int number = getInt();
        System.out.println(number);
    }
}

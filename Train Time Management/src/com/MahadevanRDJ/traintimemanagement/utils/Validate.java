package com.MahadevanRDJ.traintimemanagement.utils;
import java.util.Scanner;


public class Validate {
    private static Scanner scanner = new Scanner(System.in);
    public static boolean check(Integer value) {
        if(value.getClass().getName().equals("I")) return false;
        return true;
    }

    public static boolean check(String value) {
        value = value.toLowerCase();
        for(char c : value.toCharArray()) if(c < 'a' || c > 'z') return false;
        return true;
    }
    public static boolean verifyPassWord(String passWord) {
        if(passWord.length() < 4) return false;
        return true;
    }
    public static String getString() {
        String string = scanner.next();
        return string;
    }
    public static int getInt() {
        return Integer.parseInt(getString());
    }
    public static void main(String[] args) {
        System.out.printf("%20s", "Hello, world!");
    }
}
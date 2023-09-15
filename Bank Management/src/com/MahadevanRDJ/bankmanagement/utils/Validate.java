package com.MahadevanRDJ.bankmanagement.utils;

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
        if(passWord.length() < 8) return false;
        return true;
    }
    public static String getString() {
        String string = scanner.next();
        return string;
    }
    public static int getInt() {
        return Integer.parseInt(getString());
    }
    public static long getContact() {
        Long contact = Long.parseLong(getString());
        if(contact.toString().length() < 10) getContact();
        return contact; 
    }
    public static int setPIN() {
        Integer pin = getInt();
        if(pin.toString().length() < 4 && pin.toString().length() > 6)  setPIN();
        return pin;
        
    }
    public static void main(String[] args) {
        String value = "hello";
        value = value.toUpperCase();
        System.out.println(value);
    }
}

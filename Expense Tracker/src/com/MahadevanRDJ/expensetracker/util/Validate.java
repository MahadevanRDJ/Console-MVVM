package com.MahadevanRDJ.expensetracker.util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static boolean verifyPassWord(String passWord) {
        if (passWord.length() < 8)
            return false;
        return true;
    }

    public static boolean isDate(String startDate, String endDate) {
        Pattern pattern = Pattern.compile("[0-9]{4}+[-]+[0-1][0-9]+[-]+[0-3][0-9]");
        Matcher m1 = pattern.matcher(startDate);
        Matcher m2 = pattern.matcher(endDate);
        if (!(m1.matches()) && m2.matches())
            return false;
        LocalDate date1 = LocalDate.parse(startDate);
        LocalDate date2 = LocalDate.parse(endDate);
        if (date1.isBefore(LocalDate.now()) || date1.isAfter(date2))
            return false;
        return true;
    }

    public static boolean isDate(String date) {
        Pattern pattern = Pattern.compile("[0-9]{4}+[-]+[0-1][0-9]+[-]+[0-3][0-9]");
        Matcher m1 = pattern.matcher(date);
        return m1.matches();
    }

    public static boolean isInteger(int n) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(n + "");
        return matcher.matches() ? true : false;

    }

    public static boolean isString(String string) {
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches() ? true : false;
    }

    public static boolean isPassword(String string) {
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_!*(){}|\\\\/\"':;<>,.?\\-])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches() ? true : false;
    }

    public static boolean isStringLine(String string) {
        Pattern pattern = Pattern.compile("[a-zA-Z ]+");
        Matcher matcher = pattern.matcher(string);
        return matcher.matches() ? true : false;
    }

    public static void main(String[] args) {
        String value = "hello";
        value = value.toUpperCase();
        System.out.println(value);
    }
}

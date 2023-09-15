package com.MahadevanRDJ.mailapplication.utils;

import java.util.Scanner;

import com.MahadevanRDJ.mailapplication.MailRepository.MailRepository;


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
        String string = scanner.nextLine();
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
    public static String getMail() {
        String mail = getString();
        if(!isMail(mail)) getMail();
        return mail;
    }
    private static boolean isMail(String mail) {
        if(mail.endsWith("@mail.com") && mail.length() > 13) {
            return true;
        }
        return false;
    }
    public static String verifyMail() {
        String mail = getMail();
        if (MailRepository.getInstance().verifyMail(mail)) verifyMail();
        return mail;
    }
    public static boolean checkMail(String mail) { 
        return MailRepository.getInstance().checkMail(mail);
    }

    public static String getLine() {
        String line = scanner.nextLine();
        return line;
    }
    public static void main(String[] args) {
        String value = "hello@gmail.com jafub";
        System.out.println(value);
    }
}

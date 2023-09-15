package com.MahadevanRDJ.mailapplication.MailPage;

import java.util.Map;
import java.util.Scanner;

import com.MahadevanRDJ.mailapplication.DTOs.CheckMail;
import com.MahadevanRDJ.mailapplication.utils.Validate;

public class MailView implements MailViewCallBack {
    private MailControllerCallBack mailController;
    private Scanner scanner = new Scanner(System.in);

    public MailView() {
        this.mailController = new MailController(this);
    }
    public static void main(String[] args) {
        MailView mView = new MailView();
        mView.init();;
    }
    public void init() {
    //    checkMails();
       onMail();
    }

    private void checkMails() {
        System.out.println("---------------------------------------------------------------------");
        mailController.checkMails();
        
    }

    @Override
    public void showCheckMails(Map<String, CheckMail> checkMails) {
        System.out.println("--------------------------MAILS------------------------");
        for ( Map.Entry<String, CheckMail> entry : checkMails.entrySet()) {
            System.out.println("\t----------------" + entry.getValue().getSubject() + "----------------");;
            System.out.println();
            System.out.println("From: " + entry.getKey());
            System.out.println(entry.getValue().getMessage());
            System.out.println();
            System.out.println("Date :" + entry.getValue().getDate() + " Time : " + entry.getValue().getTime());
        }
    }
    public void onMail() {
        mailController.getMail();
    }
    private void onMyMail(String mail) {
        System.out.println("----------------------------------------------------------------");
        int choice;

        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Compose Mails");
            System.out.println("2. EXIT");
            choice = 0;
			while(!scanner.hasNextInt()) {
				scanner.nextLine();
				System.out.println("Choice: ");
			} 
			choice = scanner.nextInt();

            switch(choice) {
               case 1: composeMail(mail); break;
               case 2: System.exit(0); break;
            }
        } while(choice != 8);


    }
    public void composeMail(String mail) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("To : ");
        String to = "";
        do {
            to = getMail();
        }while(!checkMail(to));
        System.out.println("Subject : ");
        String subject = getString();

        System.out.println("Message : ");
        String message = getString();

        System.out.println("Send : [Y/N]");
        if(getString().equalsIgnoreCase("y"))mailController.sendMail(mail, to, subject, message);
        else onMail();
    }
    private String getMail() {
        return Validate.getMail();
    }
    private boolean checkMail(String mail) {
        return Validate.checkMail(mail);
    }
    private String getString() {
        return Validate.getString();
    }
    @Override
    public void onMail(String mail) {
        onMyMail(mail);
    }
    
}





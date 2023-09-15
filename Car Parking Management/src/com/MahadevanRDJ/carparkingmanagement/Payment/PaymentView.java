package com.MahadevanRDJ.carparkingmanagement.Payment;

import java.util.Scanner;

public class PaymentView  {
    private PaymentViewModel paymentViewModel;
    private Scanner scanner = new Scanner(System.in);

    public PaymentView() {
        this.paymentViewModel = new PaymentViewModel(this);
    }

    public void init() {
        paymentMethods();
    }

    private void paymentMethods() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("PaymentMethods");
        System.out.println("1. UPI");
        System.out.println("2. CreditCard/DebitCard");
        System.out.println("3. Net Banking");
        System.out.println("Enter Your Choice : ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                payUsingUPI();
                break;
            case 2:
                payUsingCards();
                break;
            case 3:
                payUsingNetBanking();
                break;
            default:
                System.out.println("Invalid option");
                paymentMethods();
                break;
        }
    }

    public void pay(int amount) {
        System.out.println("--------------------------------");
        System.out.println("Dear Customer,the amount to be paid  is " + amount + " INR.");
        System.out.println("Press '1' to proceed.");
        System.out.println("Press '2' to cancel");
        System.out.println("Choice : ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Please Wait...Your payment is processing...");
                System.out.println("Your payment is successfull.");
                System.exit(0);
                break;
            case 2:
                System.out.println("You've cancel the payment");
                System.out.println("Redirecting to main page...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option...");
                break;
        }
    }

    private void payUsingUPI() {
        paymentViewModel.getAmount();
    }

    private void payUsingCards() {
        paymentViewModel.getAmount();
    }

    private void payUsingNetBanking() {
        paymentViewModel.getAmount();
    }

}

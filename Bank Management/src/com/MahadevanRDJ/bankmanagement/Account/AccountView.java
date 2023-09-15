package com.MahadevanRDJ.bankmanagement.Account;

import java.util.List;
import java.util.Scanner;

import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Transactions;
import com.MahadevanRDJ.bankmanagement.Login.LoginView;
import com.MahadevanRDJ.bankmanagement.utils.Validate;

public class AccountView {
    private AccountViewModel accountViewModel;
    private Scanner scanner = new Scanner(System.in);
    private static boolean isPinSet;

    public AccountView() {
        this.accountViewModel = new AccountViewModel(this);
    }

    public void init() {
        int choice;
        System.out.println("----------------------------------------------------");
        System.out.println("1. Account details");
        System.out.println("2. Create Pin");
        System.out.println("3. Main Page");
        System.out.println("4. EXIT");
        System.out.println("Choice :");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Choice: ");
        }
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                getAccount();
                break;
            case 2:
                newAccount();
                break;
            case 3:
                mainPage();
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Invalid choice: ");
        }
    }

    private void mainPage() {
        LoginView lView = new LoginView();
        lView.UserManagerLogin();
    }

    public void newAccount() {
        int choice;
        do {
            System.out.println("----------------------------------------------------");
            System.out.println("1. Set PIN");
            System.out.println("2. Main Menu");
            System.out.println("3. EXIT");
            System.out.println("Choice : ");
            choice = 0;
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Choice: ");
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    if (!isPinSet)
                        setPin();
                    break;
                case 2:
                    if (isPinSet)
                        init();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 3);
    }

    private void setPin() {
        System.out.println("-----------SET PIN --------");
        System.out.println("Your Account Number : ");
        int accountNumber = getInt();
        checkAccount(accountNumber);
        System.out.println("Enter PIN : ");
        int pin = Validate.setPIN();
        System.out.println("Re-Enter PIN : ");
        int rPin = Validate.setPIN();
        if (pin == rPin)
            accountViewModel.setPin(accountNumber, pin);
        isPinSet = true;
    }

    private void getAccount() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Your Account Number : ");
        int accountNumber = getInt();
        if (!checkAccount(accountNumber)) {
            System.out.println("Un-Authorized Account Number or pin number is not set.");
        }
        accountViewModel.getAccount(accountNumber);
    }

    private boolean checkAccount(int accountNumber) {
        System.out.println("----------------------------------------------------------------");
        if (accountViewModel.checkAccount(accountNumber))
            return true;
        else
            return false;
    }

    public void onAccount(Account account) {
        int choice;
        if (account.getPin() == 0)
            newAccount();
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Account Details");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Transaction History");
            System.out.println("6. Main menu");
            System.out.println("Choice : ");
            choice = 0;
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Choice: ");
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    accountDetails(account);
                    break;
                case 2:
                    depositMoney(account);
                    break;
                case 3:
                    withDrawMoney(account);
                    break;
                case 4:
                    transferMoney(account);
                    break;
                case 5:
                    transactionHistory(account);
                    break;
                case 6:
                    init();
                    break;
            }
        } while (choice != 6);

    }

    private void accountDetails(Account account) {
        System.out.println("--------------Account Details--------------");
        System.out.println("Account Name : " + account.getAccountName());
        System.out.println("Account Number : " + account.getAccountNumber());
        System.out.println("Account Balance : " + account.getAccountBalance());
        System.out.println("Branch : " + account.getBranch());
    }

    private void depositMoney(Account account) {
        System.out.println("--------------Deposit Money------------------");
        int accountNumber = account.getAccountNumber();
        System.out.println("Amount :");
        int amount = getInt();
        accountViewModel.depositMoney(accountNumber, amount);
    }

    private void withDrawMoney(Account account) {
        System.out.println("--------------Deposit Money------------------");
        int accountNumber = account.getAccountNumber();
        System.out.println("Enter PIN :");
        int pin = getInt();
        if (account.getPin() == pin) {
            System.out.println("Amount :");
            int amount = getInt();
            accountViewModel.withDrawMoney(accountNumber, amount);
        }

    }

    private void transferMoney(Account account) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Target Account Number: ");
        int targetAccountNumber = getInt();
        int sourceAccountNumber = account.getAccountNumber();
        if (checkAccount(targetAccountNumber)) {
            System.out.println("Amount :");
            int amount = getInt();
            accountViewModel.transferMoney(sourceAccountNumber, targetAccountNumber, amount);
        }
    }

    private void transactionHistory(Account account) {
        accountViewModel.transactionHistory(account.getAccountNumber());
    }

    private int getInt() {
        return Validate.getInt();
    }

    public void accountNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Account Not Found : ");
    }

    public void depositMoneyLimitExceeded() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Deposit money should not be greater than 30000 INR.");
    }

    public void showTransactionHistory(List<Transactions> transactions) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("------------------Transaction History------------------------");
        System.out.println("TransactionTime\t\t\tTransaction ID\tTranscationType");
        try {
            for (Transactions transaction : transactions) {
                System.out.println(transaction.getTransactionTime() + "\t\t" + transaction.getTransactionID()
                        + "     \t" + transaction.getTransactionType());
            }
        } catch (Exception e) {
            System.out.println("No Initial transaction is found");
        }
    }

    public void withDrawMoney(int withdrawn) {
        System.out.println("----------------------------------------------------------------");
        System.out.println(withdrawn + " INR, is debited from your account.");
    }

    public void inSufficientMoney() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Minimum balance of 500 INR should be maintained.");
    }

    public void depositedMoney(int amount) {
        System.out.println("----------------------------------------------------------------");
        System.out.println(amount + " INR, is credited to your account.");
    }

    public void transferFailed() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Minimum balance of 500 INR should be maintained.");
    }

    public void transferSuccessful(int transferred) {
        System.out.println("----------------------------------------------------------------");
        System.out.println(transferred + " INR, is debited from your account.");
    }

    public void pinSetSuccess() {
        System.out.println("----------------------------------------------------");
        System.out.println("Set Pin is successful.");
    }
}

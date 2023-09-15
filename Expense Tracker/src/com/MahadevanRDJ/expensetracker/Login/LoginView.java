package com.MahadevanRDJ.expensetracker.Login;

import java.util.Scanner;

import com.MahadevanRDJ.expensetracker.DTOs.User;
import com.MahadevanRDJ.expensetracker.Transactions.TransactionsView;
import com.MahadevanRDJ.expensetracker.Wallet.WalletView;
import com.MahadevanRDJ.expensetracker.util.Resource;

public class LoginView {
    private LoginViewModel loginViewModel;
    private Scanner scanner = new Scanner(System.in);

    public LoginView() {
        this.loginViewModel = new LoginViewModel(this);
    }

    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.init();
    }

    private void init() {
        int choice;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Sign In");
            System.out.println("2. Sign Up");
            System.out.println("3. Forgot Password");
            System.out.println("4. EXIT");
            System.out.println("Choice : ");
            choice = 0;
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Choice: ");
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    signIn();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    forgotPassword();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 5);
    }

    private String getString() {
        return Resource.getString();
    }

    private String getPassword() {
        return Resource.getPassword();
    }

    private void signUp() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Sign Up: ");
        System.out.println("First Name: ");
        String firstName = getString();
        System.out.println("Last Name: ");
        String lastName = getString();
        System.out.println("Display Name: ");
        String userName = getString();
        System.out.println("Password: ");
        String password = getPassword();
        loginViewModel.addUser(firstName, lastName, userName, password);
    }

    private void signIn() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Sign In :");
        System.out.println("User name : ");
        String userName = getString();
        System.out.println("Password : ");
        String password = getPassword();
        loginViewModel.userLogin(userName, password);
    }

    private void forgotPassword() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Reset Password : ");
        System.out.println("----------------------------------------------------------------");
        System.out.println("To reset your password, we require your USERNAME to check your credentials.");

        System.out.println("User Name : ");
        String userName = getString();
        if (loginViewModel.toResetPassword(userName)) {
            System.out.println("---------Reset Password--------- ");
            System.out.println("New Password : ");
            String password = getPassword();
            System.out.println("----------------------------------------------------------------");
            System.out.println((loginViewModel.resetPassword(userName, password))
                    ? "Password reset successfully"
                    : "Password reset failed");
        } else {
            System.out.println("----------------------------------------------------------------");
            System.out.println("User not found");
        }
    }

    public void adminLoginSucceed() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome Admin!");
        goToWallet();
    }

    public void adminLoginFailed() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("You're not a admin!");
        init();
    }

    public void userLoginFailed() {
        System.out.println("Sign in failed!");
        System.out.println("Invalid username or password!");
        System.out.println("Reset Password? [Y -> for yes and else -> for no]");
        System.out.println("Choice: ");
        if (scanner.next().equalsIgnoreCase("Y"))
            forgotPassword();
        else
            init();
    }

    public void userLoginSucceed(User user) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome " + user.getUserName() + "!.");
        if (user.isWalletInitialized())
            goToTransactions();
        else
            goToWallet();
    }

    private void goToTransactions() {
        TransactionsView transactionsView = new TransactionsView();
        transactionsView.init();
    }

    private void goToWallet() {
        WalletView wView = new WalletView();
        wView.init();
    }

    public void userAddedSuccessfully() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome!. Your registration is sucessful.");
    }

}

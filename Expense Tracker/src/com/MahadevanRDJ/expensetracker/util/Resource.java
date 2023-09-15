package com.MahadevanRDJ.expensetracker.util;

import java.time.LocalDate;
import java.util.Scanner;

import com.MahadevanRDJ.expensetracker.ExpenseTrackerRepository.ExpenseTrackerRepository;
import com.MahadevanRDJ.expensetracker.Login.LoginView;
import com.MahadevanRDJ.expensetracker.Login.LoginViewModel;
import com.MahadevanRDJ.expensetracker.Wallet.WalletView;

public class Resource {
    private static LoginViewModel loginViewModel;
    private static Scanner scanner = new Scanner(System.in);

    public static void logout() {
        loginViewModel.logOut();
        LoginView.main(null);
    }

    public static int getInt() {
        int n = scanner.nextInt();
        if (!Validate.isInteger(n))
            getInt();
        return n;
    }

    public static String getString() {
        String string = scanner.next();
        if (!Validate.isString(string))
            getString();
        return string;
    }

    public static String getStringLine() {
        scanner.nextLine();
        String string = scanner.nextLine();
        if (!Validate.isStringLine(string))
            getStringLine();
        return string;
    }

    public static String getPassword() {
        String string = scanner.next();
        if (!Validate.isPassword(string))
            getPassword();
        return string;

    }

    public static LocalDate getDate() {
        String date = scanner.next();
        if (!Validate.isDate(date))
            getDate();
        return LocalDate.parse(date);
    }

    public static boolean getWallet() {
        if (ExpenseTrackerRepository.getInstance().getWallet() != null) {
            WalletView wView = new WalletView();
            wView.displayWallet();
            return true;
        }
        return false;
    }
}

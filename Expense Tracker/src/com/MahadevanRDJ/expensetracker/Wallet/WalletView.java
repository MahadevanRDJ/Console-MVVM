package com.MahadevanRDJ.expensetracker.Wallet;

import com.MahadevanRDJ.expensetracker.DTOs.Wallet;
import com.MahadevanRDJ.expensetracker.Transactions.TransactionsView;
import com.MahadevanRDJ.expensetracker.util.Resource;

public class WalletView {
    private WalletViewModel walletViewModel;
    private boolean isWalletInitialized = false;

    public WalletView() {
        this.walletViewModel = new WalletViewModel(this);
    }

    public static void main(String[] args) {
        WalletView wView = new WalletView();
        wView.init();
    }

    public void init() {
        int choice;
        boolean back = true;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Initialize wallet");
            System.out.println("2. Display Wallet");
            System.out.println("3. Add Transaction");
            System.out.println("4. Back");
            System.out.println("5. EXIT");
            choice = getInt();
            switch (choice) {
                case 1:
                    initiateWallet();
                    break;
                case 2:
                    if (isWalletInitialized)
                        displayWallet();
                    break;
                case 3:
                    if (isWalletInitialized)
                        transactions();
                    break;
                case 4:
                    back = false;
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        } while (back);

    }

    private void initiateWallet() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-----------------------WALLET INITIALIZATION--------------------");
        System.out.println("Wallet Name: ");
        String name = getString();
        System.out.println("Initial Amount: ");
        int amount = getInt();
        walletViewModel.initiate(name, amount);
        isWalletInitialized = true;
    }

    public void displayWallet() {
        walletViewModel.displayWallet();
    }

    private String getString() {
        return Resource.getString();
    }

    private int getInt() {
        return Resource.getInt();
    }

    public void displayWallet(Wallet wallet) {
        if (wallet == null) {
            System.out.println("Wallet is not initialized!");
            return;
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("------------------------Wallet Details--------------------------");
        System.out.println("Wallet ID : " + wallet.getWalletId());
        System.out.println("Wallet Name: " + wallet.getWalletName());
        System.out.println("Wallet Amount: " + wallet.getAmount());
    }

    private void transactions() {
        TransactionsView tView = new TransactionsView();
        tView.init();
    }
}

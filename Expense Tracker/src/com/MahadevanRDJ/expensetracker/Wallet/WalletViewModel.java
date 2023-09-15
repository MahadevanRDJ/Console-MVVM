package com.MahadevanRDJ.expensetracker.Wallet;

import com.MahadevanRDJ.expensetracker.DTOs.Wallet;
import com.MahadevanRDJ.expensetracker.ExpenseTrackerRepository.ExpenseTrackerRepository;

public class WalletViewModel {
    private WalletView walletView;

    public WalletViewModel(WalletView walletView) {
        this.walletView = walletView;
    }

    public void initiate(String name, int amount) {
        ExpenseTrackerRepository.getInstance().initiateWallet(name, amount);
        returnWallet();
    }

    private void returnWallet() {
        Wallet wallet = ExpenseTrackerRepository.getInstance().getWallet();
        walletView.displayWallet(wallet);
    }

    public void displayWallet() {
        returnWallet();
    }
}
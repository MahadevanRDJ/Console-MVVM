package com.MahadevanRDJ.bankmanagement.Account;

import java.util.List;

import com.MahadevanRDJ.bankmanagement.BankRepository.BankRepository;
import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Transactions;

public class AccountViewModel {
    private AccountView accountView;

    public AccountViewModel(AccountView accountView) {
        this.accountView = accountView;
    }

    
    public void getAccount(int accountNumber) {
        Account account = BankRepository.getInstance().getAccount(accountNumber);
        if (account != null) {
            accountView.onAccount(account);
        } else {
            accountView.accountNotFound();
        }
    }

    
    public boolean checkAccount(int accountNumber) {
        Account account = BankRepository.getInstance().getAccount(accountNumber);
        if (account != null) {
            return true;
        } else {
            return false;
        }
    }

    
    public void transferMoney(int sourceAccountNumber, int targetAccountNumber, int amount) {
        int transferred = BankRepository.getInstance().transferMoney(sourceAccountNumber, targetAccountNumber, amount);
        if(transferred != -1) {
            accountView.transferSuccessful(transferred);
        } else {
            accountView.transferFailed();
        }
    }

    
    public void setPin(int accountNumber, int pin) {
        BankRepository.getInstance().setPin(accountNumber, pin);
        accountView.pinSetSuccess();
    }

    
    public void depositMoney(int accountNumber, int amount) {
        BankRepository.getInstance().depositMoney(accountNumber, amount);
        accountView.depositedMoney(amount);
    }

    
    public void withDrawMoney(int accountNumber, int amount) {
        int withdrawn = BankRepository.getInstance().withDrawMoney(accountNumber, amount);
        if(withdrawn != -1) {
            accountView.withDrawMoney(withdrawn);
        } else {
            accountView.inSufficientMoney();
        }
    }

    
    public void transactionHistory(int accountNumber) {
        List<Transactions> transactions = BankRepository.getInstance().getTransactions(accountNumber);
        accountView.showTransactionHistory(transactions);
        
    }
    
}

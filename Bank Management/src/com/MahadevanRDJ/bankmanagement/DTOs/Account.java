package com.MahadevanRDJ.bankmanagement.DTOs;

public class Account {

    private int pin;
    private String accountName;
    private int accountNumber;
    private int accountBalance;
    private String branch;
    private int customerID;
    private boolean isApproved;
    public Account(boolean isApproved, String accountName, int accountNumber, int accountBalance, String branch) {
        this.isApproved = isApproved;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.branch = branch;
    }
    public Account(boolean isApproved, String accountName, int accountNumber, int accountBalance, String branch, int pin) {
        this.isApproved = isApproved;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.branch = branch;
        this.pin = pin;
    }

    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public int getAccountBalance() {
        return accountBalance;
    }
    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }
    public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }    

    
}

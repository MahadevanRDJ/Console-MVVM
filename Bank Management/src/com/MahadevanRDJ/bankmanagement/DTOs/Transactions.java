package com.MahadevanRDJ.bankmanagement.DTOs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Transactions {
    private int transactionID;
    private String transactionType;
    private String transactionTime;
    
    public Transactions(String transactionType) {
        this.transactionID = new Random().nextInt(100000, 200000);
        this.transactionType = transactionType;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.transactionTime = LocalDateTime.now().format(formatter);
    }
    public int getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public String getTransactionTime() {
        return transactionTime;
    }
    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    
}

package com.MahadevanRDJ.expensetracker.DTOs;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Transactions {
    private int transactionID;
    private String transactionName;
    private String transactionCategory;
    private LocalDate transactionDate;
    private BigDecimal amount;
    private Status status;

    public Transactions(ResultSet resultSet) throws SQLException {
        this.transactionID = resultSet.getInt(1);
        this.transactionCategory = resultSet.getString(2);
        this.transactionName = resultSet.getString(3);
        this.amount = resultSet.getBigDecimal(4);
        this.transactionDate = resultSet.getDate(5).toLocalDate();
        this.status = Status.valueOf(resultSet.getString(6));
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public void display() {
        System.out.printf("%20s%20s%20s%20s%20s",
                transactionID,
                transactionName,
                transactionDate,
                amount,
                status);
        System.out.println();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}

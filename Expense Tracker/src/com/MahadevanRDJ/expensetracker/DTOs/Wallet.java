package com.MahadevanRDJ.expensetracker.DTOs;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Wallet {
    private int walletId;
    private String walletName;
    private BigDecimal amount;
    private LocalDateTime date;

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Wallet(ResultSet resultSet) throws SQLException {
        this.walletId = resultSet.getInt(1);
        this.walletName = resultSet.getString(2);
        this.amount = resultSet.getBigDecimal(4);
        this.date = resultSet.getTimestamp(5).toLocalDateTime();
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

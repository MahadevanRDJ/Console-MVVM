package com.MahadevanRDJ.expensetracker.DTOs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private int user_id;
    private String userFirstName;
    private String userLastName;
    private String userName;
    private String password;
    private boolean isWalletInitialized;

    public User(ResultSet resultSet) throws SQLException {
        this.user_id = resultSet.getInt(1);
        this.userName = resultSet.getString(4);
        this.password = resultSet.getString(5);
        this.isWalletInitialized = resultSet.getBoolean(6) ? true : false;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserName() {
        return userName;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isWalletInitialized() {
        return isWalletInitialized;
    }

    public void setWalletInitialized(boolean isWalletInitialized) {
        this.isWalletInitialized = isWalletInitialized;
    }

}

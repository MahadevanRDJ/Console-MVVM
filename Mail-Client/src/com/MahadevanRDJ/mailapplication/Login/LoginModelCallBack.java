package com.MahadevanRDJ.mailapplication.Login;

public interface LoginModelCallBack {

    void checkAdmin(String adminName, String adminPassword);

    void addUser(String firstName, String lastName, String displayName, String password);

    void checkUser(String userName, String password);

    boolean toResetPassword(String userName);

    void resetPassword(String userName, String password);
    
}

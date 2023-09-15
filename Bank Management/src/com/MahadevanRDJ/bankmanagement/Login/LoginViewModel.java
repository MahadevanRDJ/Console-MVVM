package com.MahadevanRDJ.bankmanagement.Login;

import com.MahadevanRDJ.bankmanagement.BankRepository.BankRepository;
import com.MahadevanRDJ.bankmanagement.DTOs.Admin;
import com.MahadevanRDJ.bankmanagement.DTOs.Manager;
import com.MahadevanRDJ.bankmanagement.DTOs.User;

public class LoginViewModel {
    private LoginView loginView;
    
    public LoginViewModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void checkAdmin(String adminName, String adminPassword) {
        Admin admin = BankRepository.getInstance().getAdmin(adminName, adminPassword);
        if(admin != null) {
            loginView.adminLoginSucceed();
        } else {
            loginView.adminLoginFailed();
        }
    }

    public void addUser(String firstName, String lastName, String userName, String password) {
        BankRepository.getInstance().addUser(firstName, lastName, userName, password);
        loginView.userAddedSuccessfully();
    }


    public void checkUser(String userName, String password) {
        User user = BankRepository.getInstance().getUser(userName, password);
        if(user != null) {
            loginView.userLoginSucceed(user);
        } else {
            loginView.userLoginFailed();
        }
        
    }

    public boolean toResetPassword(String userName) {
        return BankRepository.getInstance().checkUserName(userName);
    }

    public void resetPassword(String userName, String password) {
        BankRepository.getInstance().resetPassword(userName, password);
    }

    public void managerLogin(String name, String password) {
        Manager manager = BankRepository.getInstance().checkManager(name, password);
        if(manager != null) { 
            loginView.welcomeManager(manager);
        } else {
            loginView.managerNotFound();
        }
    }
}


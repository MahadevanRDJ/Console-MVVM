package com.MahadevanRDJ.traintimemanagement.Login;

import com.MahadevanRDJ.traintimemanagement.DTOs.Admin;
import com.MahadevanRDJ.traintimemanagement.DTOs.User;
import com.MahadevanRDJ.traintimemanagement.TrainRepository.TrainRepository;

public class LoginViewModel{
    private LoginView loginView;
    
    public LoginViewModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void checkAdmin(String adminName, String adminPassword) {
        Admin admin = TrainRepository.getInstance().adminLogin(adminName, adminPassword);
        if(admin != null) {
            loginView.adminLoginSucceed();
        } else {
            loginView.adminLoginFailed();
        }
    }

    public void addUser(String firstName, String lastName, String userName, String password) {
        TrainRepository.getInstance().addUser(firstName, lastName, userName, password);
        loginView.userAddedSuccessfully();
    }


    public void checkUser(String userName, String password) {
        User user = TrainRepository.getInstance().userLogin(userName, password);
        if(user != null) {
            loginView.userLoginSucceed(user);
        } else {
            loginView.userLoginFailed();
        }
        
    }

    public boolean toResetPassword(String userName) {
        return TrainRepository.getInstance().checkUser(userName);
    }

    public void resetPassword(String userName, String password) {
        TrainRepository.getInstance().resetPassword(userName, password);
    }
    
}


package com.MahadevanRDJ.carparkingmanagement.Login;

import com.MahadevanRDJ.carparkingmanagement.CarParkingRepository.CarParkingRepository;
import com.MahadevanRDJ.carparkingmanagement.DTOs.Admin;
import com.MahadevanRDJ.carparkingmanagement.DTOs.User;

public class LoginViewModel  {
    private LoginView loginView;
    
    public LoginViewModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void checkAdmin(String adminName, String adminPassword) {
        Admin admin = CarParkingRepository.getInstance().getAdmin(adminName, adminPassword);
        if(admin != null) {
            loginView.adminLoginSucceed();
        } else {
            loginView.adminLoginFailed();
        }
    }


    public void addUser(String firstName, String lastName, String userName, String password) {
        CarParkingRepository.getInstance().addUser(firstName, lastName, userName, password);
        loginView.userAddedSuccessfully();
    }


    public void checkUser(String userName, String password) {
        User user = CarParkingRepository.getInstance().getUser(userName, password);
        if(user != null) {
            loginView.userLoginSucceed(user);
        } else {
            loginView.userLoginFailed();
        }
        
    }


    public boolean toResetPassword(String userName) {
        return CarParkingRepository.getInstance().checkUserName(userName);
    }


    public void resetPassword(String userName, String password) {
        CarParkingRepository.getInstance().resetPassword(userName, password);
    }


    
}

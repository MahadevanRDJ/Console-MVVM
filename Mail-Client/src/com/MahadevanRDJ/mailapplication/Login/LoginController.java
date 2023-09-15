package com.MahadevanRDJ.mailapplication.Login;

import com.MahadevanRDJ.mailapplication.DTOs.User;

public class LoginController implements LoginControllerCallBack, LoginModelControllerCallBack {
    private LoginModelCallBack loginModel;
    private LoginViewCallBack loginView;

    public LoginController(LoginViewCallBack loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModel(this);
    }

    @Override
    public void checkAdmin(String adminName, String adminPassword) {
        loginModel.checkAdmin(adminName, adminPassword);
    }

    @Override
    public void adminLoginSucceed() {
        loginView.adminLoginSucceed();
    }

    @Override
    public void adminLoginFailed() {
        loginView.adminLoginFailed();
    }

    @Override
    public void addUser(String firstName, String lastName, String userName, String password) {
        loginModel.addUser(firstName, lastName, userName, password);
    }

    @Override
    public void checkUser(String userName, String password) {
       loginModel.checkUser(userName, password);
    }

    @Override
    public void userLoginFailed() {
       loginView.userLoginFailed();
       
    }

    @Override
    public void userLoginSucceed(User user) {
        loginView.userLoginSucceed(user);
    }

    @Override
    public boolean toResetPassword(String userName) {
       return loginModel.toResetPassword(userName);
    
    }

    @Override
    public void resetPassword(String userName, String password) {
        loginModel.resetPassword(userName, password);
    }

    @Override
    public void userAddedSuccessfully() {
        loginView.userAddedSuccessfully();
    }
    
}

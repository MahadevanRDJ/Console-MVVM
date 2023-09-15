package com.MahadevanRDJ.mailapplication.Login;

import com.MahadevanRDJ.mailapplication.DTOs.Admin;
import com.MahadevanRDJ.mailapplication.DTOs.User;
import com.MahadevanRDJ.mailapplication.MailRepository.MailRepository;

public class LoginModel implements LoginModelCallBack {
    private LoginModelControllerCallBack loginController;
    
    public LoginModel(LoginModelControllerCallBack loginController) {
        this.loginController = loginController;
    }

    @Override
    public void checkAdmin(String adminName, String adminPassword) {
        Admin admin = MailRepository.getInstance().adminLogin(adminName, adminPassword);
        if(admin != null) {
            loginController.adminLoginSucceed();
        } else {
            loginController.adminLoginFailed();
        }
    }

    @Override
    public void addUser(String firstName, String lastName, String userName, String password) {
        MailRepository.getInstance().addUser(firstName, lastName, userName, password);
        loginController.userAddedSuccessfully();
    }


    @Override
    public void checkUser(String userName, String password) {
        User user = MailRepository.getInstance().userLogin(userName, password);
        if(user != null) {
            loginController.userLoginSucceed(user);
        } else {
            loginController.userLoginFailed();
        }
        
    }

    @Override
    public boolean toResetPassword(String userName) {
        return MailRepository.getInstance().checkUser(userName);
    }

    @Override
    public void resetPassword(String userName, String password) {
        MailRepository.getInstance().resetPassword(userName, password);
    }

    
}
interface LoginModelControllerCallBack {

    void adminLoginSucceed();

    void userAddedSuccessfully();

    void userLoginFailed();

    void userLoginSucceed(User user);

    void adminLoginFailed();

}

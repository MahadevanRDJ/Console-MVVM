package com.MahadevanRDJ.expensetracker.Login;

import com.MahadevanRDJ.expensetracker.DTOs.User;
import com.MahadevanRDJ.expensetracker.ExpenseTrackerRepository.ExpenseTrackerRepository;

public class LoginViewModel {
    private LoginView loginView;

    public LoginViewModel(LoginView loginView) {
        this.loginView = loginView;
    }

    public void addUser(String firstName, String lastName, String userName, String password) {
        ExpenseTrackerRepository.getInstance().addUser(firstName, lastName, userName, password);
        loginView.userAddedSuccessfully();
    }

    public void userLogin(String userName, String password) {
        User user = ExpenseTrackerRepository.getInstance().userLogin(userName, password);
        if (user != null) {
            loginView.userLoginSucceed(user);
        } else {
            loginView.userLoginFailed();
        }
    }

    public boolean toResetPassword(String userName) {
        return ExpenseTrackerRepository.getInstance().checkUserName(userName);
    }

    public boolean resetPassword(String userName, String password) {
        return ExpenseTrackerRepository.getInstance().resetPassword(userName, password);
    }

    public void logOut() {
        ExpenseTrackerRepository.getInstance().logOut();
    }

}

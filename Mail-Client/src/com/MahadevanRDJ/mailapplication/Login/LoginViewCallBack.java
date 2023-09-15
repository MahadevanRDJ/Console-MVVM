package com.MahadevanRDJ.mailapplication.Login;

import com.MahadevanRDJ.mailapplication.DTOs.User;

public interface LoginViewCallBack {

    void adminLoginSucceed();

    void adminLoginFailed();

    void userLoginFailed();

    void userLoginSucceed(User user);

    void userAddedSuccessfully();
    
}

package com.MahadevanRDJ.mailapplication.MailPage;

import java.util.Map;

import com.MahadevanRDJ.mailapplication.DTOs.CheckMail;

public interface MailViewCallBack {

    void showCheckMails(Map<String, CheckMail> checkMails);

    void onMail(String mail);
    
}

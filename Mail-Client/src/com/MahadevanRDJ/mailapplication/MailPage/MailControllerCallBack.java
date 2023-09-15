package com.MahadevanRDJ.mailapplication.MailPage;

public interface MailControllerCallBack {

    void checkMails();

    void getMail();

    void sendMail(String mail, String to, String subject, String message);
    
}

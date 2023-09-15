package com.MahadevanRDJ.mailapplication.MailPage;

import java.util.Map;

import com.MahadevanRDJ.mailapplication.DTOs.CheckMail;

public class MailController implements MailControllerCallBack, MailModelControllerCallBack {
    private MailModelCallBack mailModel;
    private MailViewCallBack mailView;

    public MailController(MailViewCallBack mailView) {
        this.mailView = mailView;
        this.mailModel = new MailModel(this);
    }

    @Override
    public void checkMails() {
        mailModel.checkMails();
    }

    @Override
    public void returnCheckMails(Map<String, CheckMail> checkMails) {
        mailView.showCheckMails(checkMails);
    }

    @Override
    public void getMail() {
        mailModel.getMail();
    }

    @Override
    public void onMail(String mail) {
        mailView.onMail(mail);
    }

    @Override
    public void sendMail(String mail, String to, String subject, String message) {
        mailModel.sendMail(mail, to, subject, message);
    }

    
}

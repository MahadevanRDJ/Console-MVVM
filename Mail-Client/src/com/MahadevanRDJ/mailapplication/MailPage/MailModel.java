package com.MahadevanRDJ.mailapplication.MailPage;

import java.util.Map;

import com.MahadevanRDJ.mailapplication.DTOs.CheckMail;
import com.MahadevanRDJ.mailapplication.MailRepository.MailRepository;

public class MailModel implements MailModelCallBack {
    private MailModelControllerCallBack mailController;

    public MailModel(MailModelControllerCallBack mailController) {
        this.mailController = mailController;
    }

    @Override
    public void checkMails() {
        Map<String, CheckMail> checkMails = MailRepository.getInstance().getCheckMails();
        mailController.returnCheckMails(checkMails);
    }

    @Override
    public void getMail() {
        String mail = MailRepository.getInstance().getMail();
        mailController.onMail(mail);
    }

    @Override
    public void sendMail(String mail, String to, String subject, String message) {
        MailRepository.getInstance().sendMail(mail, to, subject, message);
    }
    
}
interface MailModelControllerCallBack{

    void returnCheckMails(Map<String, CheckMail> checkMails);

    void onMail(String mail);

}
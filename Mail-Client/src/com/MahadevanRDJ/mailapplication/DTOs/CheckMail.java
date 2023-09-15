package com.MahadevanRDJ.mailapplication.DTOs;

public class CheckMail {
    private String mail;
    private String subject;
    private String message;
    private String date;
    private String time;

    
    public CheckMail(String mail, String subject, String message, String date, String time) {
        this.mail = mail;
        this.subject = subject;
        this.message = message;
        this.date = date;
        this.time = time;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    
}

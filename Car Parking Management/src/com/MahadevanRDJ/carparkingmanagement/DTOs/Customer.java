package com.MahadevanRDJ.carparkingmanagement.DTOs;

import java.util.Random;

public class Customer {
    private byte customerId;
    private String customerName;
    private String gender;
    private long contact;
    private String city;

    public Customer(String customerName, String gender, long contact, String city) {
        this.customerId = (byte) new Random().nextInt(1000, 2000);
        this.customerName = customerName;
        this.gender = gender;
        this.contact = contact;
        this.city = city;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public byte getCustomerId() {
        return customerId;
    }

    public void setCustomerId(byte customerId) {
        this.customerId = customerId;
    }

}

package com.MahadevanRDJ.bankmanagement.DTOs;

import java.util.Random;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String city;
    private String branch;
    private long contact;
    private boolean isApproved;
    

    public Customer(String firstName, String lastName, int age, String gender, String city, String branch, long contact) {
                this.customerID = new Random().nextInt(10, 100) + new Random().nextInt(100);
                this.firstName = firstName;
                this.lastName = lastName;
                this.age = age;
                this.gender = gender;
                this.city = city;
                this.branch = branch;
                this.contact = contact;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public long getContact() {
        return contact;
    }
    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String approved() {
        if(!isApproved) return "Not Approved"; 
        return "Approved";
    }
    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }
    
    


}

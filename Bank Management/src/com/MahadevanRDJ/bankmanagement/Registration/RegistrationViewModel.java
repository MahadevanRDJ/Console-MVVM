package com.MahadevanRDJ.bankmanagement.Registration;

import com.MahadevanRDJ.bankmanagement.BankRepository.BankRepository;
import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public class RegistrationViewModel  {
    private RegistrationView registrationView;
    public RegistrationViewModel(RegistrationView registrationView) {
        this.registrationView = registrationView;
    }
   
    public void addCustomer(String firstName, String lastName, int age, String gender, String city, String district, long contact) {
        int customerID = BankRepository.getInstance().addCustomer(firstName, lastName, age, gender, city, district, contact);
        registrationView.resgistrationSuccess(customerID);
    }
   
    public void displayCustomer(int customerID) {
        Customer customer = BankRepository.getInstance().getCustomer(customerID);
        if(customer != null) {
            registrationView.showCustomer(customer);
        } else {
            registrationView.customerNotFound();
        }
    }
    
}

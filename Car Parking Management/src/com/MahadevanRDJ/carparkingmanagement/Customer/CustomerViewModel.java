package com.MahadevanRDJ.carparkingmanagement.Customer;

import com.MahadevanRDJ.carparkingmanagement.CarParkingRepository.CarParkingRepository;


public class CustomerViewModel  {
    private CustomerView customerView;
    public CustomerViewModel(CustomerView customerView) {
        this.customerView = customerView;
    }
    
    public void addCustomer(String customerName, String gender, long contact, String city) {
        CarParkingRepository.getInstance().addCustomerDetails(customerName, gender, contact, city);
        customerView.displayCustomerDetails(CarParkingRepository.getInstance().getCustomer());
    }
    
    public void addCar(String carName, String carNumber) {
        CarParkingRepository.getInstance().addCar(carName, carNumber);
        customerView.displayCarDetails(CarParkingRepository.getInstance().getCar());
    }  
}


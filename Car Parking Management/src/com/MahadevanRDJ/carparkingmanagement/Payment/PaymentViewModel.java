package com.MahadevanRDJ.carparkingmanagement.Payment;

import com.MahadevanRDJ.carparkingmanagement.CarParkingRepository.CarParkingRepository;

public class PaymentViewModel {
    private PaymentView paymentView;

    public PaymentViewModel(PaymentView paymentView) {
        this.paymentView = paymentView;
    }

    public void getAmount() {
        int amount = CarParkingRepository.getInstance().totalFare();
        paymentView.pay(amount);
    }

}

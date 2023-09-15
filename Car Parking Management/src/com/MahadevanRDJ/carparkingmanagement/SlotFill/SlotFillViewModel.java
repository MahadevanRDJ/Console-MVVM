package com.MahadevanRDJ.carparkingmanagement.SlotFill;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.carparkingmanagement.CarParkingRepository.CarParkingRepository;
import com.MahadevanRDJ.carparkingmanagement.DTOs.ParkingSlot;

public class SlotFillViewModel{
    private SlotFillView slotFillView;
    private LocalDate currentDate = LocalDate.now();

    public SlotFillViewModel(SlotFillView slotFillView) {
        this.slotFillView = slotFillView;
    }

    public void checkDate(LocalDate userDate) {
        int difference = userDate.compareTo(currentDate);
        try {
            Period period = Period.between(currentDate, userDate);
            if(period.getDays() > 7 || difference < 0) {
                slotFillView.inValidDate();
            } else {
                setDate(userDate);
                slotFillView.validDate(userDate);
            }
            
        } catch (Exception e) {
            System.out.println("Invalid date / Not a leap year.");
            slotFillView.inValidDate();
        }
    }
    public void setDate(LocalDate userDate) {
       CarParkingRepository.getInstance().setDate(userDate);
    }

    public void setTime(LocalTime userTime) {
        CarParkingRepository.getInstance().setTime(userTime);
    }

    public void getSlots() {
       slotFillView.displaySlots(CarParkingRepository.getInstance().returnAllDaySlots());
    }

    public void setSlotDate(String slotDate) {
        ParkingSlot slot = CarParkingRepository.getInstance().getSlotDate(slotDate);
        if(slot != null) {
            slotFillView.chooseSlots(slot);
        } else {
            slotFillView.slotNotFound();
        }
    }

    public void setSlotTime(int slotTime, String slotDate) {
        ParkingSlot slot = CarParkingRepository.getInstance().setTimeSlot(slotTime, slotDate);
        if(slot != null) {
            slotFillView.showSlot(slot);
        } else {
            slotFillView.slotNotAvailable();
        }
    }

    public void totalFare() {
        int total = CarParkingRepository.getInstance().totalFare();
        slotFillView.totalFare(total);
    }

    public void removeSlot(String slotDate, int slotTime) {
        boolean isRemoved = CarParkingRepository.getInstance().removeSlot(slotDate, slotTime);
        if(isRemoved) {
            slotFillView.removedSuccessfully();
        } else {
            slotFillView.notRemoved();
        }
    }

    public void displayBookedSlots() {
        Map<String, List<Integer>> bookedSlots = CarParkingRepository.getInstance().getBookedSlots();
        slotFillView.displayBookedSlots(bookedSlots);

    }
    
}

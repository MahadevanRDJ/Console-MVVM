package com.MahadevanRDJ.carparkingmanagement.DTOs;

import java.util.ArrayList;
import java.util.List;

public class ParkingSlot {
    private final int FARE = 250;
    private String slotName;
    private static int numberOfSlots;
    private List<Integer> slots = new ArrayList<Integer>();

    public ParkingSlot() {
    }
    public ParkingSlot(String slotName, List<Integer> slots) {
        this.slotName = slotName;
        this.slots = slots;
    }

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }


    public List<Integer> getSlots() {
        return slots;
    }

    public void setSlots(List<Integer> slots) {
        this.slots = slots;
    }

    public int getFare() {
        return FARE;
    }

    public int getFARE() {
        return FARE;
    }

    public static int getNumberOfSlots() {
        return numberOfSlots;
    }

    public static void setNumberOfSlots(int numberOfSlots) {
        ParkingSlot.numberOfSlots = numberOfSlots;
    }

    
}

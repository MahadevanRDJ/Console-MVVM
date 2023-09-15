package com.MahadevanRDJ.traintimemanagement.DTOs;

public class Schedule {
    private int scheduleID;
    private int trainID;
    private String arrivalTime;
    private String departureTime;
    
    public Schedule(int scheduleID, int trainID, String arrivalTime, String departureTime) {
        this.scheduleID = scheduleID;
        this.trainID = trainID;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }
    public int getScheduleID() {
        return scheduleID;
    }
    public void setScheduleID(int scheduleID) {
        this.scheduleID = scheduleID;
    }
    public int getTrainID() {
        return trainID;
    }
    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    
}

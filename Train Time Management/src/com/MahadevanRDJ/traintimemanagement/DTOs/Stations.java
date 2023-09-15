package com.MahadevanRDJ.traintimemanagement.DTOs;


public class Stations {
    private String arrivalTime;
    private String departureTime;
    private String stationName;
    public Stations() {
    }
    
    public Stations(String arrivalTime, String departureTime, String stationName) {
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stationName = stationName;
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
    public String getStationName() {
        return stationName;
    }
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    
}

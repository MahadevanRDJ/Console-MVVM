package com.MahadevanRDJ.traintimemanagement.DTOs;

public class Train implements Cloneable {
    private String trainName;
    private int trainNumber;
    private static String sourceCode;
    private static String destinationCode;
    private static String sourcePlace;
    private static String destinationPlace;
    private String sourceTime;
    private String destinationTime;

    public Train() {
    }

    public Train(String trainName, int trainNumber) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public static String getSourcePlace() {
        return sourcePlace;
    }

    public void setSourcePlace(String sourcePlace) {
        Train.sourcePlace = sourcePlace;
    }

    public static String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        Train.destinationPlace = destinationPlace;
    }

    public static String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        Train.sourceCode = sourceCode;
    }

    public static String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        Train.destinationCode = destinationCode;
    }

    public String getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(String sourceTime) {
        this.sourceTime = sourceTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

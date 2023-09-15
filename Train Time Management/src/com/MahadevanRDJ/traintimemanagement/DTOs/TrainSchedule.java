package com.MahadevanRDJ.traintimemanagement.DTOs;


public class TrainSchedule implements Cloneable{
    private int trainID;
    private String sourceCode;
    private String destinationCode;
    private String sourcePlace;
    private String destinationPlace;
    private String sourceTime;
    private String destinationTime;
    

    public TrainSchedule(){
    }
    
    public TrainSchedule(int trainID,String sourceCode, String destinationCode, String sourcePlace, String destinationPlace, String sourceTime,
            String destinationTime) {
        this.trainID = trainID;
        this.sourceCode = sourceCode;
        this.destinationCode = destinationCode;
        this.sourcePlace = sourcePlace;
        this.destinationPlace = destinationPlace;
        this.sourceTime = sourceTime;
        this.destinationTime = destinationTime;
    }
   
    public int getTrainID() {
        return trainID;
    }
    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }
    public String getSourcePlace() {
        return sourcePlace;
    }
    public void setSourcePlace(String sourcePlace) {
        this.sourcePlace = sourcePlace;
    }
    public String getDestinationPlace() {
        return destinationPlace;
    }
    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }
    
    public String getSourceCode() {
        return sourceCode;
    }
    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
    public String getDestinationCode() {
        return destinationCode;
    }
    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
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

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

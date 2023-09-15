package com.MahadevanRDJ.traintimemanagement.Train;

import java.util.List;

import com.MahadevanRDJ.traintimemanagement.DTOs.Stations;
import com.MahadevanRDJ.traintimemanagement.DTOs.Train;
import com.MahadevanRDJ.traintimemanagement.TrainRepository.TrainRepository;

public class TrainViewModel {
    private TrainView trainView;

    public TrainViewModel(TrainView trainView) {
        this.trainView = trainView;
    }

    public void getTrains(String from, String to) {
        List<Train> schedule = TrainRepository.getInstance().getTrainSchedule(from, to);
        if (schedule != null) {
            trainView.showTrainSchedule(schedule);
        } else {
            trainView.noTrainSchedule();
        }
    }

    public void getStations(int trainNumber) {
        List<Stations> stations = TrainRepository.getInstance().getStations(trainNumber);
        trainView.displayStations(stations);
    }

    public List<Train> allTrains() {
        return TrainRepository.getInstance().getAllTrains();
    }

}

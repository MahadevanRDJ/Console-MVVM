package com.MahadevanRDJ.traintimemanagement.Train;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import com.MahadevanRDJ.traintimemanagement.DTOs.Stations;
import com.MahadevanRDJ.traintimemanagement.DTOs.Train;
import com.MahadevanRDJ.traintimemanagement.utils.Validate;

public class TrainView {
    private TrainViewModel trainViewModel;
    private Scanner scanner = new Scanner(System.in);

    public TrainView() {
        this.trainViewModel = new TrainViewModel(this);
    }

    public static void main(String[] args) {
        TrainView trainView = new TrainView();
        trainView.init();
    }

    public void init() {
        int choice = 0;

        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. All trains");
            System.out.println("2. Search train");
            System.out.println("3. EXIT");
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Choice: ");
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    allTrains();
                    break;
                case 2:
                    searchTrain();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        } while (choice != 2);

    }

    private void searchTrain() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("From :");
        String from = getString();
        if (!Validate.check(from))
            searchTrain();
        System.out.println("To :");
        String to = getString();
        if (!Validate.check(to))
            searchTrain();
        if (!from.equalsIgnoreCase(to)) {
            trainViewModel.getTrains(from, to);
        } else {
            System.out.println("Both sourece and destination can't be same.");
            searchTrain();
        }
    }

    private String getString() {
        return Validate.getString();
    }

    public void showTrainSchedule(List<Train> allSchedule) {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("                **** SCHEDULED TRAIN ****                         ");
        System.out.println("\n\t\t" + Train.getSourcePlace() + "---->" + Train.getDestinationPlace());
        System.out.println();
        System.out.println("TrainTD \t Train Name \tDeparing time \t Reaching time");
        for (Train trainSchedule : allSchedule) {
            System.out.print(trainSchedule.getTrainNumber() + "\t   ");
            System.out.print(trainSchedule.getTrainName() + "\t   ");
            System.out.print(trainSchedule.getSourceTime() + "\t\t");
            System.out.print(trainSchedule.getDestinationTime() + "\t\t");
            System.out.println();
        }

        onTrain(allSchedule);
    }

    private void onTrain(List<Train> allSchedule) {
        int choice;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Search routes and timings");
            System.out.println("2. Main menu");
            System.out.println("3. EXIT");
            System.out.println("Choice :");
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Choice: ");
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showStations(allSchedule);
                    break;
                case 2:
                    init();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 3);
    }

    private void allTrains() {
        List<Train> allTrains = trainViewModel.allTrains();
        show(allTrains);
    }

    private void show(List<Train> allTrains) {
        Stream<Train> stream = allTrains.stream();
        System.out.printf("%20s %20s", "Train Number", "Train Name\n");
        stream.forEach((train) -> {
            System.out.printf("%20s %30s", train.getTrainNumber(), train.getTrainName() + "\n");
        });
    }

    private void showStations(List<Train> allSchedule) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Train Number: ");
        int trainNo = getInt();
        for (Train train : allSchedule) {
            if (train.getTrainNumber() == trainNo) {
                trainViewModel.getStations(trainNo);
                return;
            }
        }
    }

    private int getInt() {
        return Validate.getInt();
    }

    public void noTrainSchedule() {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("No train has been scheduled yet.");
    }

    public void displayStations(List<Stations> stations) {
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Arrival Time \t Station \t Departure Time");
        for (Stations station : stations) {
            if (!station.getArrivalTime().startsWith("-"))
                System.out.print(station.getArrivalTime() + "\t\t");
            else
                System.out.print("\t\t");
            System.out.print(station.getStationName() + "\t\t");
            if (!station.getDepartureTime().startsWith("-"))
                System.out.println(station.getDepartureTime());
        }
        System.out.println();
    }
}

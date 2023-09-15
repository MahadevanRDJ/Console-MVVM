package com.MahadevanRDJ.traintimemanagement.TrainRepository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.MahadevanRDJ.traintimemanagement.DTOs.Admin;
import com.MahadevanRDJ.traintimemanagement.DTOs.Stations;
import com.MahadevanRDJ.traintimemanagement.DTOs.Train;
import com.MahadevanRDJ.traintimemanagement.DTOs.User;

public class TrainRepository {
    private static TrainRepository trainInstance;

    private Admin admin;
    private User user;
    private List<Train> allSchedule = new ArrayList<>();
    private List<Integer> train;
    private Train scheduledTrain = new Train();
    private Map<String, String> stations = new LinkedHashMap<String, String>();
    private List<Train> allTrain;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private CallableStatement callableStatement;
    private String query;

    private TrainRepository() {
    }

    public void createConnection() {
        String url = "jdbc:mysql://localhost:3306/train";
        String usermail = "root";
        String password = "ArunEswari3#";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, usermail, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TrainRepository getInstance() {
        if (trainInstance == null) {
            trainInstance = new TrainRepository();
            trainInstance.createConnection();
            trainInstance.addStationName();
            return trainInstance;
        }
        trainInstance.allSchedule.clear();
        return trainInstance;
    }

    public Admin adminLogin(String adminName, String password) {
        query = "Select * from admin Where name='" + adminName + "' and password='" + password + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                admin = new Admin(adminName, password);
                return admin;
            }
            statement.close();
        } catch (SQLException e) {
        }
        return null;
    }

    public void addUser(String firstName, String lastName, String username, String password) {
        // query = "INSERT INTO user VALUES (" + firstmail + ", " + lastmail + ", " +
        // usermail + ", " + password + ")";
        query = "INSERT INTO user(Firstname, Lastname, Username, Password) VALUES (?, ?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, username);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User userLogin(String userName, String password) {
        query = "Select * from user Where username='" + userName + "' and password='" + password + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                user = new User(userName, password);
                return user;
            }
            statement.close();
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean checkUser(String userName) {
        query = "select * from user where username = '" + userName + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public void resetPassword(String userName, String password) {
        String querySelect = "SELECT * FROM  user where username = '" + userName + "' ";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querySelect);
            if (resultSet.next()) {
                query = "Update user SET password = ' " + password + "' where username = '" + userName + "'";
                preparedStatement = connection.prepareStatement(query);

                preparedStatement.executeUpdate();

                preparedStatement.close();
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addStationName() {
        query = "Select * from stations";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                stations.put(resultSet.getString(1), resultSet.getString(2));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
        }
    }

    public List<Train> getTrainSchedule(String from, String to) {
        from = from.toUpperCase();
        to = to.toUpperCase();
        String sourcePlace_query = "SELECT * FROM stations where station_name = '" + from + "'";
        String destinationPlace_query = "SELECT * FROM stations WHERE station_name = '" + to + "'";
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sourcePlace_query);
            while (resultSet.next()) {
                scheduledTrain.setSourcePlace(from);
                scheduledTrain.setSourceCode(resultSet.getString(1));
            }

            resultSet = statement.executeQuery(destinationPlace_query);
            while (resultSet.next()) {
                scheduledTrain.setDestinationPlace(to);
                scheduledTrain.setDestinationCode(resultSet.getString(1));
            }

            statement.close();
            trainchedule();
            return allSchedule;
        } catch (SQLException e) {
        }
        return null;
    }

    private void trainchedule() {
        String sourceStationCode = Train.getSourceCode();
        String destinationStationCode = Train.getDestinationCode();
        int toTime = 0, fromTime = 0;

        train = new ArrayList<>();

        if (Train.getSourcePlace() != null && Train.getDestinationPlace() != null) {
            query = "Select * from schedule where stationcode = '" + sourceStationCode
                    + "' and trainno in (Select DISTINCT trainno from schedule)";

            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    train.add(resultSet.getInt(2));
                }
                for (int trainNo : train) {
                    fromTime = getTime(sourceStationCode, 5, trainNo);
                    if (fromTime == -9999)
                        continue;
                    toTime = getTime(destinationStationCode, 4, trainNo);
                    if (toTime < fromTime)
                        continue;
                    scheduledTrain.setTrainNumber(trainNo);
                    setDepartedTime(fromTime + "");
                    setReachedTime(toTime + "");
                    setTrainName(trainNo);

                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    private void setTrainName(int trainNumber) {
        query = "select * from train where train_no = " + trainNumber;

        try {
            Train temp = (Train) scheduledTrain.clone();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                temp.setTrainName(resultSet.getString(2));
                temp.setTrainNumber(resultSet.getInt(1));
            }
            allSchedule.add(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<Stations> getStations(int trainNumber) {
        int fromTime = 0, toTime = 0;
        String arrivalTime = "";
        String departureTime = "";
        String station = "";
        List<Stations> betweenStations = new ArrayList<Stations>();

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * From schedule where trainno = " + trainNumber);

            while (resultSet.next()) {
                fromTime = getTime(Train.getSourceCode(), 5, trainNumber);
                toTime = getTime(Train.getDestinationCode(), 4, trainNumber);

                int t_fromTime = getTime(resultSet.getString(3), 5, trainNumber);
                while ((t_fromTime < fromTime)) {
                    t_fromTime = getTime(resultSet.getString(3), 5, trainNumber);
                    resultSet.next();
                }

                arrivalTime = convertTime(resultSet.getString(4));
                departureTime = convertTime(resultSet.getString(5));
                station = stations.get(resultSet.getString(3));

                betweenStations.add(new Stations(arrivalTime, departureTime, station));
                while (resultSet.next()) {
                    if ((fromTime <= toTime)) {
                        arrivalTime = convertTime(resultSet.getString(4));
                        departureTime = convertTime(resultSet.getString(5));
                        station = stations.get(resultSet.getString(3));
                        betweenStations.add(new Stations(arrivalTime, departureTime, station));

                        fromTime = getTime(resultSet.getString(3), 5, trainNumber);
                    }
                }
            }
        } catch (NumberFormatException e) {
        } catch (SQLException e) {
        }
        return betweenStations;
    }

    private int getTime(String stationCode, int column, int trainNo) {
        String time = "";
        String[] times;
        try {
            String query = "Select * from schedule where trainno = " + trainNo
                    + " and stationcode = '" + stationCode + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                time = rs.getString(column);
            }
            if (time.length() < 2)
                return -9999;
            times = time.split(":");
            time = "";
            for (String string : times) {
                time += string;
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
        }
        return Integer.parseInt(time);
    }

    public List<Train> getAllTrains() {
        try {
            allTrain = new ArrayList<Train>();
            callableStatement = connection.prepareCall("{CALL get_all_trains()}");
            resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                allTrain.add(new Train(resultSet.getString(2), resultSet.getInt(1)));
            }
            resultSet.close();
            callableStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allTrain;
    }

    public String convertTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2)) % 24;
        String min = time.substring(3);
        return hour + ":" + min;
    }

    private void setReachedTime(String destinaionTime) {
        String hour = Integer.parseInt(destinaionTime.substring(0, 2)) % 24 + "";
        String min = destinaionTime.substring(2, 4);
        scheduledTrain.setDestinationTime(hour + ":" + min);
    }

    private void setDepartedTime(String sourceTime) {
        String hour = Integer.parseInt(sourceTime.substring(0, 2)) % 24 + "";
        String min = sourceTime.substring(2, 4);
        scheduledTrain.setSourceTime(hour + ":" + min);
    }
}
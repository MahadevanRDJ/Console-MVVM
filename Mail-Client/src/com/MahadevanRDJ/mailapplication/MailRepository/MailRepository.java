package com.MahadevanRDJ.mailapplication.MailRepository;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import com.MahadevanRDJ.mailapplication.DTOs.Admin;
import com.MahadevanRDJ.mailapplication.DTOs.CheckMail;
import com.MahadevanRDJ.mailapplication.DTOs.Mail;
import com.MahadevanRDJ.mailapplication.DTOs.User;

public class MailRepository {
    private static MailRepository mailInstance;

    private Admin admin;
    private User user;
    private Map<String, CheckMail> checkMails = new LinkedHashMap<String, CheckMail>();
    private Mail mail;

    private String query;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection connection;
    private MailRepository() {
    }

    public  void createConnection()  {
        String url = "jdbc:mysql://localhost:3306/mail";
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


    public static MailRepository getInstance() {
        if (mailInstance == null) {
            mailInstance = new MailRepository();
            mailInstance.createConnection();
            return mailInstance;
        }
        return mailInstance;
    }

    public Admin adminLogin(String mail, String password)  {
        query = "Select * from admin Where mail='" + mail + "' and password='" + password + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                admin = new Admin(mail , password);
                return admin;
            }
            statement.close();
        } catch (SQLException e) {
            
        }        
        return null;
    }
    public void addUser(String firstName, String lastName, String newMail, String password) {
        // query = "INSERT INTO user VALUES (" + firstmail + ", " + lastmail + ", " + usermail + ", " + password + ")";
        query = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";
        int id = new Random().nextInt(0, Integer.MAX_VALUE);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, newMail);
            preparedStatement.setString(5, password);
            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User userLogin(String mailID, String password)  {
        query = "Select * from user Where mail='" + mailID + "' and password='" + password + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
                user = new User(mailID , password);
                user.setUserFirstName(resultSet.getString(2));
                user.setUserLastName(resultSet.getString(3));
                return user;
            }
            statement.close();
        } catch (SQLException e) {
        }
        return null;
    }
    public boolean checkUser(String mail)  {
        query = "Select * from user Where usermail='" + mail + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if(resultSet.next()) {
               return true;
            }
            statement.close();
        } catch (SQLException e) {
        }
        return false;
    }
    
    public void resetPassword(String mail, String password) {
        if (mail.equals(user.getMail())) {
            user.setPassword(password);
        }
    }

    public Map<String, CheckMail> getCheckMails() {
        query = "Select * from checkmail";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                checkMails.put(resultSet.getString(1), 
                new CheckMail(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)));
            }

            return checkMails;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean checkMail(String mail) {
        query = "Select * from checkmail where mailFrom = '" + mail + "'";

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    public String getMail() {
        return user.getMail();
    }

    public void sendMail(String from, String to, String subject, String message) {
        query = "INSERT INTO allMail VALUES (?, ?, ?, ?, ?, ?, ?)";
        int id = new Random().nextInt(0, Integer.MAX_VALUE);
        try {
            mail = new Mail(from, to, subject, message);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, mail.getfrom());
            preparedStatement.setString(3, mail.getTo());
            preparedStatement.setString(4, mail.getSubject());
            preparedStatement.setString(5, mail.getMessage());
            preparedStatement.setDate(6, mail.getDate());
            preparedStatement.setTime(7, mail.getTime());

            

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    private void addToChecMails() {
        
    }
    public boolean verifyMail(String mail) {
        query = "Select * from user where mail = '" + mail + "'"; 
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }
}
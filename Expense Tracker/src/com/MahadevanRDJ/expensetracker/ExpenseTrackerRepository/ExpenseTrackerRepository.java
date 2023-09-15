package com.MahadevanRDJ.expensetracker.ExpenseTrackerRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

import com.MahadevanRDJ.expensetracker.DTOs.Status;
import com.MahadevanRDJ.expensetracker.DTOs.Transactions;
import com.MahadevanRDJ.expensetracker.DTOs.User;
import com.MahadevanRDJ.expensetracker.DTOs.Wallet;

public class ExpenseTrackerRepository {
    private static ExpenseTrackerRepository expenseInstance;

    private User user;
    private List<String> expenses = new ArrayList<String>();
    private List<String> debts = new ArrayList<String>();
    private List<String> incomes = new ArrayList<String>();
    private List<Transactions> transactions;
    private Wallet wallet;

    private String query;
    private Connection connection;
    private CallableStatement callableStatement;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    protected ExpenseTrackerRepository() {
    }

    public static ExpenseTrackerRepository getInstance() {
        if (expenseInstance == null) {
            expenseInstance = new ExpenseTrackerRepository();
            expenseInstance.init();
            return expenseInstance;
        }
        return expenseInstance;
    }

    private void init() {
        expenseInstance.expenseList();
        expenseInstance.debtList();
        expenseInstance.incomeList();
        expenseInstance.createConnection();
    }

    private void createConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/expense_db";
        String user = "root";
        String password = "ArunEswari3#";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(String firstName, String lastName, String userName, String password) {
        query = "{call add_user(?, ?, ?, ?)}";
        boolean flag = false;
        try {
            callableStatement = connection.prepareCall(query);

            callableStatement.setString(1, firstName);
            callableStatement.setString(2, lastName);
            callableStatement.setString(3, userName);
            callableStatement.setString(4, password);

            flag = callableStatement.executeUpdate() > 0 ? true : false;
            callableStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public User userLogin(String name, String password) {
        query = "Select * from users Where user_name='" + name + "' and password='" + password + "'";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                user = new User(resultSet);
                if (user.isWalletInitialized()) {
                    query = "SELECT * FROM wallet WHERE user_id = " + user.getUser_id();
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(query);
                    if (resultSet.next())
                        wallet = new Wallet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public void initiateWallet(String walletName, int amount) {
        query = "{call initiate_wallet(?, ?, ?, ?)}";
        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
        try {
            callableStatement = connection.prepareCall(query);

            callableStatement.setString(1, walletName);
            callableStatement.setInt(2, user.getUser_id());
            callableStatement.setBigDecimal(3, BigDecimal.valueOf(amount));
            callableStatement.setTimestamp(4, date);
            callableStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Wallet getWallet() {
        query = "SELECT * from wallet WHERE user_id = " + user.getUser_id();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                wallet = new Wallet(resultSet);
            }
            if (user.isWalletInitialized()) {
                query = "{call get_walletAmount(?)}";
                callableStatement = connection.prepareCall(query);
                callableStatement.setInt(1, user.getUser_id());
                callableStatement.execute();
                resultSet = callableStatement.getResultSet();
                if (resultSet.next()) {
                    wallet.setAmount(resultSet.getBigDecimal(1) == null ? wallet.getAmount()
                            : resultSet.getBigDecimal(1).add(wallet.getAmount()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                resultSet.close();
                statement.close();
                callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return wallet;
    }

    public boolean checkUserName(String userName) {
        query = "SELECT * FROM users WHERE user_name = '" + userName + "'";
        boolean flag = false;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                flag = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean resetPassword(String userName, String password) {
        query = "UPDATE users SET password = '" + password + "' WHERE user_name = '" + userName + "'";
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(query);
            if (preparedStatement.executeUpdate() > 0)
                flag = true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public boolean removeWallet() {
        query = "{call remove_wallet(?)}";
        boolean flag = false;
        try {
            callableStatement = connection.prepareCall(query);
            callableStatement.setInt(1, user.getUser_id());
            if (callableStatement.executeUpdate() > 0) {
                wallet = null;
                flag = true;
                user.setWalletInitialized(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    private void expenseList() {
        Scanner ipFile = null;
        try {
            ipFile = new Scanner(
                    new File("src\\com\\MahadevanRDJ\\expensetracker\\Expenses.txt"));

            while (ipFile.hasNextLine())
                expenses.add(ipFile.next());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ipFile.close();
        }
    }

    private void debtList() {
        debts.add("ReturnDebt");
        debts.add("EMI");
        debts.add("Loans");
        debts.add("Others");
    }

    private void incomeList() {
        incomes.add("Salary");
        incomes.add("Interests");
        incomes.add("Credited");
        incomes.add("Others");
    }

    public List<String> getExpenseList() {
        return expenses;
    }

    public List<String> getDebtList() {
        return debts;
    }

    public List<String> getIncomeList() {
        return incomes;
    }

    public String getExpense(int expense) {
        for (int i = 0; i < expenses.size(); i++) {
            if (i == expense - 1)
                return expenses.get(i);
        }
        return null;
    }

    public String getIncome(int income) {
        for (int i = 0; i < incomes.size(); i++) {
            if (i == income - 1)
                return incomes.get(i);
        }
        return null;
    }

    public String getDebt(int debt) {
        for (int i = 0; i < debts.size(); i++) {
            if (i == debt - 1)
                return debts.get(i);
        }
        return null;
    }

    public boolean addTransactions(int identifier, String type, LocalDate transactionDate, int amount) {
        boolean flag = false;
        query = "{call add_transactions(?, ?, ?, ?, ?, ?, ?)}";

        try {
            if (wallet != null) {
                callableStatement = connection.prepareCall(query);
                callableStatement.setInt(1, user.getUser_id());
                callableStatement.setInt(2, wallet.getWalletId());
                callableStatement.setByte(3, (byte) identifier);
                callableStatement.setString(4, type);
                callableStatement.setDate(6, Date.valueOf(transactionDate));
                if (identifier < 101) {
                    callableStatement.setBigDecimal(5, BigDecimal.valueOf(amount * -1));
                    callableStatement.setString(7, String.valueOf(Status.DEBIT));
                } else {
                    callableStatement.setBigDecimal(5, BigDecimal.valueOf(amount));
                    callableStatement.setString(7, String.valueOf(Status.CREDIT));
                }

                if (callableStatement.executeUpdate() > 0)
                    flag = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return flag;
    }

    public List<Transactions> getTransactions() {
        query = "SELECT transaction_id, category, name, t_amount, t_date, status FROM transactions WHERE user_id = "
                + user.getUser_id();
        transactions = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                transactions.add(new Transactions(resultSet));
            }
            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean removeTransaction(int transaction_id) {
        query = "DELETE FROM transactions WHERE transaction_id = " + transaction_id;
        boolean flag = false;
        try {
            preparedStatement = connection.prepareStatement(query);
            if (preparedStatement.executeUpdate() > 0)
                flag = true;
        } catch (Exception e) {
            System.out.println(e);
        } {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public void logOut() {
        user = null;
    }

}

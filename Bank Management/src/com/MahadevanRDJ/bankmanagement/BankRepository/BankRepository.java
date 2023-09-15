package com.MahadevanRDJ.bankmanagement.BankRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Admin;
import com.MahadevanRDJ.bankmanagement.DTOs.Customer;
import com.MahadevanRDJ.bankmanagement.DTOs.Manager;
import com.MahadevanRDJ.bankmanagement.DTOs.Transactions;
import com.MahadevanRDJ.bankmanagement.DTOs.User;

public class BankRepository {
    private static BankRepository bankInstance;

    private List<Admin> admin = new ArrayList<Admin>();
    private List<User> users = new ArrayList<User>();
    private List<Customer> customers = new ArrayList<Customer>();
    private Manager manager;
    private List<Account> accounts = new ArrayList<Account>();
    private Map<Integer, List<Transactions>> transactions = new LinkedHashMap<Integer, List<Transactions>>();
    private List<Transactions> currentAccTransactions = new ArrayList<Transactions>();

    private BankRepository() {
    }

    public static BankRepository getInstance() {
        if (bankInstance == null) {
            bankInstance = new BankRepository();
            return bankInstance;
        }
        return bankInstance;
    }

    static {
        BankRepository.getInstance().defaultAdmins();
        BankRepository.getInstance().initialAccounts();
    }

    private void defaultAdmins() {
        admin.add(new Admin("Deva", "DevaRDJ3"));
        admin.add(new Admin("Zoho", "GSTenkasi"));
    }

    public Admin getAdmin(String adminName, String adminPassword) {
        for (Admin admin : admin) {
            if (adminName.equalsIgnoreCase(admin.getAdminName()) && adminPassword.equals(admin.getAdminPassword())) {
                return admin;
            }
        }
        return null;
    }

    public void addUser(String usersFirstname, String usersLastname, String usersName, String password) {
        users.add(new User(usersFirstname, usersLastname, usersName, password));
    }

    public User getUser(String usersName, String password) {
        for (User user : users) {
            if (usersName.equalsIgnoreCase(user.getUserName()) && password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    public boolean checkUserName(String usersName) {
        for (User user : users) {
            if (usersName.equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }

    public void resetPassword(String usersName, String password) {
        for (User user : users) {
            if (usersName.equals(user.getUserName())) {
                user.setPassword(password);
            }
        }
    }

    private void BankManagers() {
        manager = new Manager(1, "Zoho", "HeadOffice");
    }

    private void initialAccounts() {
        accounts.add(new Account(true, "Arun", 134556, 5500, "Tenkasi", 2200));
        accounts.add(new Account(true, "Hema", 226787, 10000, "Chennai", 2120));
        accounts.add(new Account(true, "Krishna", 334353, 73000, "Peravurani", 4550));
    }

    public int addCustomer(String firstName, String lastName, int age, String gender, String city, String district,
            long contact) {
        customers.add(new Customer(firstName, lastName, age, gender, city, district, contact));
        return customers.get(customers.size() - 1).getCustomerID();
    }

    public Customer getCustomer(int customerID) {
        for (Customer customer : customers) {
            if (customerID == customer.getCustomerID()) {
                return customer;
            }
        }
        return null;
    }

    public List<Account> returnBranchAccounts(String branch) {
        List<Account> returnAccounts = new ArrayList<Account>();
        for (Account account : accounts) {
            if (account.getBranch().toLowerCase().equals(branch.toLowerCase())) {
                returnAccounts.add(account);
            }
        }
        return returnAccounts;
    }

    public List<Account> returnAccounts() {
        return accounts;
    }

    public Manager checkManager(String name, String password) {
        BankManagers();
        if (manager.getName().equals(name) && manager.getPassword().equals(password))
            return manager;
        else
            return null;
    }

    public List<Customer> returnCustomers() {
        return customers;
    }

    public void addAccount(Customer customer) {
        if (customer.getBranch().toLowerCase().equals("tenkasi")) {
            accounts.add(new Account(true, customer.getFirstName(), new Random().nextInt(100000, 199999), 0,
                    customer.getBranch()));
        } else if (customer.getBranch().toLowerCase().equals("chennai")) {
            accounts.add(new Account(true, customer.getFirstName(), new Random().nextInt(200000, 299999), 0,
                    customer.getBranch()));
        } else {
            accounts.add(new Account(true, customer.getFirstName(), new Random().nextInt(300000, 399999), 0,
                    customer.getBranch()));
        }
        accounts.get(accounts.size() - 1).setCustomerID(customer.getCustomerID());
    }

    public Account returnAccount(int customerID) {
        for (Account account : accounts) {
            if (customerID == account.getCustomerID()) {
                return account;
            }
        }
        return null;
    }

    public Account getAccount(int accountNumber) {
        for (Account account : accounts) {
            if (accountNumber == account.getAccountNumber()) {
                return account;
            }
        }
        return null;
    }

    public int transferMoney(int sourceAccountNumber, int targetAccountNumber, int amount) {
        int transferred = -1;
        for (Account account : accounts) {
            if (sourceAccountNumber == account.getAccountNumber() && account.getAccountBalance() - amount > 500) {
                account.setAccountBalance(account.getAccountBalance() - amount);
                currentAccTransactions.add(new Transactions("Transferred"));
                transactions.put(sourceAccountNumber, currentAccTransactions);
        
                transferred = amount;
            }
            if (targetAccountNumber == account.getAccountNumber()) {
                account.setAccountBalance(account.getAccountBalance() + amount);
            }
        }
        return transferred;
    }

    public void setPin(int accountNumber, int pin) {
        for (Account account : accounts) {
            if (accountNumber == account.getAccountNumber()) {
                account.setPin(pin);
            }
        }

    }

    public void depositMoney(int accountNumber, int amount) {
        for (Account account : accounts) {
            if (accountNumber == account.getAccountNumber()) {
                account.setAccountBalance(account.getAccountBalance() + amount);
                currentAccTransactions.add(new Transactions("Deposited"));
                transactions.put(accountNumber, currentAccTransactions);
            }
        }
    }

    public int withDrawMoney(int accountNumber, int amount) {
        int withdrawal = -1;
        for (Account account : accounts) {
            if (accountNumber == account.getAccountNumber() && account.getAccountBalance() - amount > 500) {
                account.setAccountBalance(account.getAccountBalance() - amount);
                currentAccTransactions.add(new Transactions("Withdrawal"));
                transactions.put(accountNumber, currentAccTransactions);
                withdrawal = amount;
            }
        }
        return withdrawal;
    }

    public List<Transactions> getTransactions(int accountNumber) {
        return transactions.get(accountNumber);
    }
}

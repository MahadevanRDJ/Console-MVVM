package com.MahadevanRDJ.bankmanagement.Manager;

import java.util.List;
import java.util.Scanner;

import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Customer;
import com.MahadevanRDJ.bankmanagement.Login.LoginView;
import com.MahadevanRDJ.bankmanagement.utils.Validate;

public class ManagerView  {
    private ManagerViewModel managerViewModel;
    private Scanner scanner = new Scanner(System.in);

    public ManagerView() {
        this.managerViewModel = new ManagerViewModel(this);
    }
    public void init() {
        int choice; 
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Display Branch Account");
            System.out.println("3. Display Registerd Account");
            System.out.println("4. Logout Manager");
            choice = 0;
			while(!scanner.hasNextInt()) {
				scanner.nextLine();
				System.out.println("Choice: ");
			} 
			choice = scanner.nextInt();
            switch(choice) {
                case 1: displayAllAccounts(); break;
                case 2: displayBranchAccounts(); break;
                case 3: displayBendingCustomers(); break;
                case 4: logout();
                default: System.out.println("Invalid choice");
            }

        } while(choice != 4);

    }
    public void displayAllAccounts() {
        managerViewModel.displayAllAccounts();
    }
    public void displayBranchAccounts() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Branch :");
        String branch = getString();
        managerViewModel.showBranchAccounts(branch);
    }
    public void displayBendingCustomers() {
        System.out.println("----------------------------------------------------------------");
        managerViewModel.displayCustomers();
    }
    private void giveApproval(Customer customer) {
        System.out.println("Approval: [Y/N]");
        if(getString().toUpperCase().equals("Y")) {
            customer.setApproved(true);
            managerViewModel.generateAccount(customer);
        }
        else return;
    }  
    private void logout() {
        LoginView lView = new LoginView();
        lView.UserManagerLogin();
    }
    private String getString() {
        return Validate.getString();
    }

    public void showBranchAccounts(List<Account> accounts) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-------------- " + accounts.get(0).getBranch() + " --------------");
        for (Account account : accounts) {
            System.out.println("Account Name : " + account.getAccountName());
            System.out.println("Account Number : " + account.getAccountNumber());
            System.out.println("Account Balance : " + account.getAccountBalance());
        }
    }
    
    public void branchNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Branch not found!.");
    }
    
    public void showAccounts(List<Account> accounts) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-------------Accounts-------------");
        for (Account account : accounts) {
            System.out.println("Account Name : " + account.getAccountName());
            System.out.println("Account Number : " + account.getAccountNumber());
            System.out.println("Account Balance : " + account.getAccountBalance());
            System.out.println("Account Branch : " + account.getBranch());
            System.out.println("----------------------------------------------------------------");
        }
    }
    
    public void showCustomers(List<Customer> customers) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-----------------Customers------------------------");
        for (Customer customer : customers) {
            System.out.println("Id: " + customer.getCustomerID());
            System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println("Age : " + customer.getAge());
            System.out.println("Gender: " + customer.getGender());
            System.out.println("City: " + customer.getCity());
            System.out.println("Branch: " + customer.getBranch());
            System.out.println("Contact: " + customer.getContact());
            System.out.println("Status: " + customer.approved());
            System.out.println("----------------------------------------------------------------");
            if(!customer.isApproved()) giveApproval(customer);
        }
    }
    
    public void accountApproved(Account account) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("------------------------Account Details-------------------------");
        System.out.println("Id: " + account.getCustomerID());
        System.out.println("Name: " + account.getAccountName());
        System.out.println("Branch : " + account.getBranch());
        System.out.println("Account Number : " + account.getAccountNumber());
    }
}

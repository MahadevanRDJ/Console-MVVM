package com.MahadevanRDJ.bankmanagement.Registration;

import java.util.Scanner;

import com.MahadevanRDJ.bankmanagement.Account.AccountView;
import com.MahadevanRDJ.bankmanagement.DTOs.Customer;
import com.MahadevanRDJ.bankmanagement.Login.LoginView;
import com.MahadevanRDJ.bankmanagement.utils.Validate;

public class RegistrationView {
    private RegistrationViewModel registrationViewModel;
    private Scanner scanner = new Scanner(System.in);
    private static boolean isAdded;

    public RegistrationView() {
        this.registrationViewModel = new RegistrationViewModel(this);

    }

    public void init() {
        int choice;
        System.out.println("1. Exist Account");
        System.out.println("2. Create Account");
        System.out.println("3. EXIT");
        System.out.println("Choice :");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Choice: ");
        }
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                existAccount();
                break;
            case 2:
                newAccount();
                break;
            case 3:
                init();
                break;
            default:
                System.out.println("Invalid choice: ");
        }
    }

    public void newAccount() {
        int choice;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Customer Form");
            System.out.println("2. Display Customer");
            System.out.println("3. Main Menu");
            choice = 0;
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Choice: ");
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    if (isAdded)
                        displayCustomer();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice: ");
            }

        } while (choice != 3);

    }
    
    private void existAccount() {
        System.out.println("----------------------------------------------------------------");
        AccountView aView = new AccountView();
        aView.init();
    }

    private void submit() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Press '1' to Submit\nElse Exit");
        int choice;
        choice = 0;
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println("Choice: ");
        }
        choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Registration completed.");
                LoginView lView = new LoginView();
                lView.UserManagerLogin();
            default:
                System.exit(0);
        }

    }

    private String getString() {
        return Validate.getString();
    }

    private int getInt() {
        return Validate.getInt();
    }

    private void addCustomer() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-------------------Registration Form----------------------------");
        System.out.println("First Name: ");
        String firstName = getString();
        System.out.println("Last Name: ");
        String lastName = getString();
        System.out.println("Age : ");
        int age = getInt();
        System.out.println("Gender : ");
        String gender = getString();
        System.out.println("City : ");
        String city = getString();
        System.out.println("Branch : ");
        String Branch = selectBranch();
        System.out.println("Contact : ");
        long contact = Validate.getContact();

        registrationViewModel.addCustomer(firstName, lastName, age, gender, city, Branch, contact);
        isAdded = true;
    }

    private void displayCustomer() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("ID :");
        int customerID = getInt();
        registrationViewModel.displayCustomer(customerID);
    }

    private void editCustomer(Customer customer) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("---EDIT---");
        int choice;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. First Name");
            System.out.println("2. Last Name");
            System.out.println("3. Age");
            System.out.println("4. Gender");
            System.out.println("5. City");
            System.out.println("6. Branch");
            System.out.println("7. Contact");
            System.out.println("8. Go to Main Menu");
            System.out.println("Choice : ");
            choice = 0;
            while (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("Choice: ");
            }
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current First Name: " + customer.getFirstName());
                    System.out.println("New First Name : ");
                    String firstName = getString();
                    customer.setFirstName(firstName);
                    break;
                case 2:
                    System.out.println("Current Last Name: " + customer.getLastName());
                    System.out.println("New Last Name : ");
                    String lastName = getString();
                    customer.setLastName(lastName);
                    break;
                case 3:
                    System.out.println("Current Age: " + customer.getAge());
                    System.out.println("New Age : ");
                    int age = getInt();
                    customer.setAge(age);
                    break;
                case 4:
                    System.out.println("Current Gender: " + customer.getGender());
                    System.out.println("Gender :");
                    String gender = getString();
                    customer.setGender(gender);
                    break;
                case 5:
                    System.out.println("Current City: " + customer.getCity());
                    System.out.println("City :");
                    String city = getString();
                    customer.setCity(city);
                    break;
                case 6:
                    System.out.println("Current Branch : " + customer.getBranch());
                    System.out.println("Branch :");
                    String Branch = getString();
                    customer.setBranch(Branch);
                    break;
                case 7:
                    System.out.println("Current Contact: " + customer.getContact());
                    System.out.println("Contact :");
                    long contact = Long.parseLong(getString());
                    customer.setContact(contact);
                    break;
                case 8:
                    System.out.println("Main menu");
                    displayCustomer();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 8);

    }

    private String selectBranch() {
        System.out.println("Branch selection");
        System.out.println("1. Tenkasi\n2. Chennai\n3. Peravurani");
        System.out.println("Select : ");
        int branchCode = getInt();
        String branch = (branchCode == 1) ? "Tenkasi"
                : (branchCode == 2) ? "Chennai" : (branchCode == 3) ? "Peravurani" : selectBranch();
        return branch;
    }

    public void resgistrationSuccess(int customerID) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Registration Success!.");
        System.out.println("For future reference, we've generated a unique CUSTOMER ID, " + customerID);
    }

    public void customerNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Customer Not Found!.");
    }

    public void showCustomer(Customer customer) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------Customer Details--------------------------");
        System.out.println("Id: " + customer.getCustomerID());
        System.out.println("Name: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Age : " + customer.getAge());
        System.out.println("Gender: " + customer.getGender());
        System.out.println("City: " + customer.getCity());
        System.out.println("Branch: " + customer.getBranch());
        System.out.println("Contact: " + customer.getContact());
        System.out.println("Status: " + customer.approved());

        System.out.println("Edit Customer : [Y/N]");
        if (getString().toUpperCase().equals("Y"))
            editCustomer(customer);

        if (customer.isApproved()) {
            AccountView aView = new AccountView();
            aView.newAccount();
        } else if (!customer.isApproved()) {
            submit();
        }

    }
}
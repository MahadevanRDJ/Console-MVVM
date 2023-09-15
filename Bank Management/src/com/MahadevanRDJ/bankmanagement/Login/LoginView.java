package com.MahadevanRDJ.bankmanagement.Login;

import java.util.Scanner;

import com.MahadevanRDJ.bankmanagement.DTOs.Manager;
import com.MahadevanRDJ.bankmanagement.DTOs.User;
import com.MahadevanRDJ.bankmanagement.Manager.ManagerView;
import com.MahadevanRDJ.bankmanagement.Registration.RegistrationView;
import com.MahadevanRDJ.bankmanagement.utils.Validate;


public class LoginView {
    private LoginViewModel loginViewModel;
    private Scanner scanner = new Scanner(System.in);

    public LoginView() {
        this.loginViewModel = new LoginViewModel(this);
    }
    
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.UserManagerLogin();
    }

    public void UserManagerLogin() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("1. User Login\n2. Manager Login");
        System.out.println("Choice: ");
        int choice = 0;
			while(!scanner.hasNextInt()) {
				scanner.nextLine();
				System.out.println("Choice: ");
			}
        choice = scanner.nextInt();
        if(choice == 1) init();
        else if(choice == 2) managerLogin();
        else UserManagerLogin();
    }

    private void managerLogin() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Name :");
        String name = getString();
        System.out.println("Password :");
        String password = getString();
        if(!Validate.verifyPassWord(password)) managerLogin();
        loginViewModel.managerLogin(name, password);

    }

    private void init() {
        int choice;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Admin Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Sign In");
            System.out.println("4. Forgot Password");
            System.out.println("5. EXIT");
            System.out.println("Choice : ");
            choice = 0;
			while(!scanner.hasNextInt()) {
				scanner.nextLine();
				System.out.println("Choice: ");
			} 
			choice = scanner.nextInt();

            switch(choice) {
                case 1: adminLogin(); break;
                case 2: signUp(); break;
                case 3: signIn(); break;
                case 4: forgotPassword();break;
                case 5: System.exit(0);
                default: System.out.println("Invalid choice");
            }

        } while(choice != 5);
    }

    private void adminLogin() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Admin Name: ");
        String adminName = getString();
        System.out.println("Password: ");
        String adminPassword = getString();
        if(!Validate.verifyPassWord(adminPassword)) adminLogin();
        loginViewModel.checkAdmin(adminName, adminPassword);
        
    }

    private String getString() {
        return Validate.getString();
    }

    private void signUp() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Sign Up: ");
        System.out.println("First Name: ");
        String firstName = getString();
        System.out.println("Last Name: ");
        String lastName = getString();
        System.out.println("Display Name: ");
        String userName = getString();
        System.out.println("Password: ");
        String password = getString();
        if(!Validate.verifyPassWord(password)) signUp();
        loginViewModel.addUser(firstName, lastName, userName, password);
    }

    private void signIn() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Sign In :");
        System.out.println("User name : " );
        String userName = getString();
        System.out.println("Password : ");
        String password = getString();
        if(!Validate.verifyPassWord(password)) signIn();
        loginViewModel.checkUser(userName, password);
    }

    private void forgotPassword() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Reset Password : ");
        System.out.println("----------------------------------------------------------------");
        System.out.println("To reset your password, we require your USERNAME to check your credentials.");

        System.out.println("User Name : ");
        String userName = getString();
        if(loginViewModel.toResetPassword(userName)) {
            System.out.println("---------Reset Password--------- ");
            System.out.println("New Password : ");
            String password = getString();
            if(!Validate.verifyPassWord(password)) signIn();
            loginViewModel.resetPassword(userName, password);
        } else {
            System.out.println("User not found");
            forgotPassword();
        }
    }

    public void adminLoginSucceed() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome Admin!");
        goToRegistrations();
    }

    public void adminLoginFailed() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("You're not a admin!");
        init();
    }

    public void userLoginFailed() {
        System.out.println("Sign in failed!");
        System.out.println("Invalid username or password!");
        System.out.println("Reset Password? [Yes/NO]");
        System.out.println("Choice: ");
        if(getString().equalsIgnoreCase("yes")) forgotPassword();
        else init();
    }

    public void userLoginSucceed(User user) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome " + user.getUserFirstName() + "!.");
        goToRegistrations();
    }

    public void userAddedSuccessfully() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome!. Your registration is sucessful.");
    }

    public void welcomeManager(Manager manager) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome " + manager.getName() + "!.");
        ManagerView mView = new ManagerView();
        mView.init();
    }

    public void managerNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("You're not a manager.");
    }
    private void goToRegistrations() {
        RegistrationView rView = new RegistrationView();
        rView.init();
    }
}


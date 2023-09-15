package com.MahadevanRDJ.mailapplication.Login;

import java.util.Scanner;

import com.MahadevanRDJ.mailapplication.DTOs.User;
import com.MahadevanRDJ.mailapplication.MailPage.MailView;
import com.MahadevanRDJ.mailapplication.utils.Validate;


public class LoginView implements LoginViewCallBack {
    private LoginControllerCallBack loginController;
    private Scanner scanner = new Scanner(System.in);

    public LoginView() {
        this.loginController = new LoginController(this);
    }
    
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.init();
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
        System.out.println("Admin Login : ");
        System.out.println("Mail: ");
        String adminMail = getMail();
        System.out.println("Password: ");
        String adminPassword = getString();
        if(!Validate.verifyPassWord(adminPassword)) adminLogin();
        loginController.checkAdmin(adminMail, adminPassword);
        
    }

    private String getString() {
        return Validate.getString();
    }
    private String getMail() {
        return Validate.getMail();
    }

    private void signUp() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("User Sign Up");
        System.out.println("First Name: ");
        String firstName = getString();
        System.out.println("Last Name: ");
        String lastName = getString();
        System.out.println("Mail: ");
        String mail = verifyMail();

        System.out.println("Password: ");
        String password = getString();
        if(!Validate.verifyPassWord(password)) signUp();
        loginController.addUser(firstName, lastName, mail, password);
    }

    private void signIn() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Sign In :");
        System.out.println("Mail : " );
        String mail = getMail();
        System.out.println("Password : ");
        String password = getString();
        if(!Validate.verifyPassWord(password)) signIn();
        loginController.checkUser(mail, password);
    }

    private void forgotPassword() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Reset Password : ");
        System.out.println("----------------------------------------------------------------");
        System.out.println("To reset your password, we require your mail to check your credentials.");

        System.out.println("User Name : ");
        String mail = getMail();
        if(loginController.toResetPassword(mail)) {
            System.out.println("---------Reset Password--------- ");
            System.out.println("New Password : ");
            String password = getString();
            if(!Validate.verifyPassWord(password)) signIn();
            loginController.resetPassword(mail, password);
        } else {
            System.out.println("User not found");
            forgotPassword();
        }
    }

    private String verifyMail() {
        return Validate.verifyMail();
    }
    @Override
    public void adminLoginSucceed() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome Admin!");
    }

    @Override
    public void adminLoginFailed() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("You're not a admin!");
        init();
    }

    @Override
    public void userLoginFailed() {
        System.out.println("Sign in failed!");
        System.out.println("Invalid mail or password!");
        System.out.println("Reset Password? [Yes/NO]");
        System.out.println("Choice: ");
        if(scanner.next().equalsIgnoreCase("yes")) forgotPassword();
        else init();
    }

    @Override
    public void userLoginSucceed(User user) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome " + user.getUserFirstName() + "!.");
        MailView mView = new MailView();
        mView.init();

    }

    @Override
    public void userAddedSuccessfully() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Welcome!. Your registration is sucessful.");
    }

}


package com.MahadevanRDJ.expensetracker.Transactions;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.MahadevanRDJ.expensetracker.DTOs.Transactions;
import com.MahadevanRDJ.expensetracker.util.Resource;

public class TransactionsView {
    private TransactionsViewModel transactionsViewModel;
    private Scanner scanner = new Scanner(System.in);

    public TransactionsView() {
        this.transactionsViewModel = new TransactionsViewModel(this);
    }

    public static void main(String[] args) {
        new TransactionsView().init();
    }

    public void init() {
        System.out.println("----------------------------------------------------------------\n");
        System.out.println("-------------------------Transaction------------------------\n");
        int choice;
        boolean back = true;
        do {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Add Transaction");
            System.out.println("2. Display Wallet");
            System.out.println("3. Remove Wallet");
            System.out.println("4. Transaction History");
            System.out.println("5. Delete Transactions");
            System.out.println("6. Back");
            System.out.println("7. Logout");
            System.out.println("0. EXIT");
            System.out.println("Choice : ");

            choice = 0;
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Choice: ");
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    Resource.getWallet();
                    break;
                case 3:
                    removeWallet();
                    break;
                case 4:
                    getAllTransactions();
                    break;
                case 5:
                    removeTransactions();
                    break;
                case 7:
                    logout();
                case 6:
                    back = false;
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid");
            }
        } while (back);
    }

    private void addTransaction() {
        if (checkWallet()) {

            System.out.println("----------------------------------------------------------------\n");
            System.out.println("-------------------------Add Transaction------------------------\n");
            boolean back = true;
            do {
                System.out.println("1. Expenses");
                System.out.println("2. Debts");
                System.out.println("3. Incomes");
                System.out.println("4. Back");
                int choice = 0;
                System.out.println("Choice : ");
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Choice: ");
                }
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        addExpense();
                        break;
                    case 2:
                        addDebt();
                        break;
                    case 3:
                        addIncome();
                        break;
                    case 4:
                        back = false;
                }
            } while (back);
        }
    }

    private void logout() {
        Resource.logout();
    }

    private boolean checkWallet() {
        return Resource.getWallet();
    }

    private void removeWallet() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-------------------------Remove Wallet--------------------------");
        System.out.println("*********Confirmation!***************");
        System.out.println("Y -> to delete  / `Press` anything to cancel ");
        if (Resource.getString().equalsIgnoreCase("y")) {
            transactionsViewModel.removeWallet();
            System.out.println("Wallet removed successfully");
        }
    }

    private void removeTransactions() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("-----------------------Remove Transaction-----------------------");
        System.out.println("Transaction ID : ");
        int transactionId = getInt();
        transactionsViewModel.removeTransaction(transactionId);
    }

    private void getAllTransactions() {
        List<Transactions> transactions = transactionsViewModel.getTransactions();
        showTransactions(transactions);
    }

    private void getExpenseList() {
        transactionsViewModel.getExpenseList();
    }

    private void getDebtList() {
        transactionsViewModel.getDebtList();
    }

    private void getIncomeList() {
        transactionsViewModel.getIncomeList();
    }

    private void addExpense() {
        getExpenseList();
        System.out.println("Select Expense in above list");
        int expense = getInt();
        transactionsViewModel.expense(expense);
    }

    private void addIncome() {
        getIncomeList();
        System.out.println("Select Income in above list.");
        int income = getInt();
        transactionsViewModel.income(income);
    }

    private void addDebt() {
        getDebtList();
        System.out.println("Select Debt in above list.");
        int debt = getInt();
        transactionsViewModel.debt(debt);
    }

    private int getInt() {
        int input = Resource.getInt();
        return input;
    }

    private LocalDate getTransactionDate() {
        System.out.println("Date Format: -> YYYY-MM-DD");
        LocalDate tDate = null;
        try {
            tDate = Resource.getDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tDate;
    }

    public void showExpenseList(List<String> expenseList) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------EXPENSES----------------------------");
        for (int i = 0; i < expenseList.size(); i++) {
            System.out.println((i + 1) + "\t" + expenseList.get(i));
        }
    }

    public void showIncomeList(List<String> incomeList) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------INCOMES----------------------------");
        for (int i = 0; i < incomeList.size(); i++) {
            System.out.println((i + 1) + "\t" + incomeList.get(i));
        }
    }

    public void showDebtList(List<String> debtList) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("----------------------------DEBTS-----------------------------");
        for (int i = 0; i < debtList.size(); i++) {
            System.out.println((i + 1) + "\t" + debtList.get(i));
        }
    }

    public void debtNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Debt not found");
    }

    public void showDebt(String debtName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Debt : " + debtName);
        System.out.println("Amount :");
        int amount = getInt();
        LocalDate transactionDate = getTransactionDate();
        transactionsViewModel.addTransaction(2, debtName, amount, transactionDate);
    }

    public void incomeNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Income not in the list try others");
    }

    public void showIncome(String incomeName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Income : " + incomeName);
        System.out.println("Amount :");
        int amount = getInt();
        LocalDate transactionDate = getTransactionDate();
        transactionsViewModel.addTransaction(101, incomeName, amount, transactionDate);
    }

    public void expenseNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Expense not in the list try others");
    }

    public void showExpense(String expenseName) {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Expense : " + expenseName);
        System.out.println("Amount :");
        int amount = getInt();
        LocalDate transactionDate = getTransactionDate();
        transactionsViewModel.addTransaction(1, expenseName, amount, transactionDate);

    }

    public void monthNotFound() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Month you've choosen is not available in the database.");
    }

    public void showTransactions(List<Transactions> transactions) {
        if (transactions.size() > 0) {
            System.out.println(
                    "----------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%20s%20s%20s%20s%20s", "ID", "Catgory", "Date", "Amoount", "Status");
            System.out.println();
            for (Transactions transaction : transactions) {
                transaction.display();
            }
        } else {
            System.out.println("----------------------------------------------------------------");
            System.out.println("No transaction found!");
        }
    }

    public void failedToRemoveTransaction() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("You've been trying to remove the invalid transaction");
    }

    public void removedSuccessfully() {
        System.out.println("----------------------------------------------------------------");
        System.out.println("Your transaction has been successfully removed");
    }

}

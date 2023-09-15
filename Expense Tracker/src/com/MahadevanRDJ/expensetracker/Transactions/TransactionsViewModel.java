package com.MahadevanRDJ.expensetracker.Transactions;

import java.time.LocalDate;
import java.util.List;

import com.MahadevanRDJ.expensetracker.DTOs.Transactions;
import com.MahadevanRDJ.expensetracker.ExpenseTrackerRepository.ExpenseTrackerRepository;

public class TransactionsViewModel {
    private TransactionsView transactionsView;

    public TransactionsViewModel(TransactionsView transactionsController) {
        this.transactionsView = transactionsController;
    }

    public void getExpenseList() {
        List<String> expenseList = ExpenseTrackerRepository.getInstance().getExpenseList();
        transactionsView.showExpenseList(expenseList);
    }

    public void getDebtList() {
        List<String> debtList = ExpenseTrackerRepository.getInstance().getDebtList();
        transactionsView.showDebtList(debtList);
    }

    public void getIncomeList() {
        List<String> incomeList = ExpenseTrackerRepository.getInstance().getIncomeList();
        transactionsView.showIncomeList(incomeList);
    }

    public void expense(int expense) {
        String expenseName = ExpenseTrackerRepository.getInstance().getExpense(expense);
        if (expenseName != null) {
            transactionsView.showExpense(expenseName);
        } else {
            transactionsView.expenseNotFound();
        }
    }

    public void income(int income) {
        String incomeName = ExpenseTrackerRepository.getInstance().getIncome(income);
        if (incomeName != null) {
            transactionsView.showIncome(incomeName);
        } else {
            transactionsView.incomeNotFound();
        }
    }

    public void debt(int debt) {
        String debtName = ExpenseTrackerRepository.getInstance().getDebt(debt);
        if (debtName != null) {
            transactionsView.showDebt(debtName);
        } else {
            transactionsView.debtNotFound();
        }
    }

    public void addTransaction(int i, String debtName, int amount, LocalDate transactionDate) {
        ExpenseTrackerRepository.getInstance().addTransactions(i, debtName, transactionDate, amount);
    }

    public List<Transactions> getTransactions() {
        return ExpenseTrackerRepository.getInstance().getTransactions();
    }

    public void removeWallet() {
        ExpenseTrackerRepository.getInstance().removeWallet();
    }

    public void removeTransaction(int transactionId) {
        boolean isRemoved = ExpenseTrackerRepository.getInstance().removeTransaction(transactionId);
        if (isRemoved) {
            transactionsView.removedSuccessfully();
        } else {
            transactionsView.failedToRemoveTransaction();
        }
    }
}

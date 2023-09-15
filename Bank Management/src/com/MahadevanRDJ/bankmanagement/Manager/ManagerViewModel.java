package com.MahadevanRDJ.bankmanagement.Manager;

import java.util.List;

import com.MahadevanRDJ.bankmanagement.BankRepository.BankRepository;
import com.MahadevanRDJ.bankmanagement.DTOs.Account;
import com.MahadevanRDJ.bankmanagement.DTOs.Customer;

public class ManagerViewModel  {
    private ManagerView managerView;
    public ManagerViewModel(ManagerView managerView) {
        this.managerView = managerView;
    }
    public void showBranchAccounts(String branch) {
        List<Account> branchedAccounts = BankRepository.getInstance().returnBranchAccounts(branch);
        if(branchedAccounts != null) { 
            managerView.showBranchAccounts(branchedAccounts);
        } else {
            managerView.branchNotFound();
        }
    }
    public void displayAllAccounts() {
        List<Account> accounts = BankRepository.getInstance().returnAccounts();
        managerView.showAccounts(accounts);
        
    }
    public void displayCustomers() {
        List<Customer> customers = BankRepository.getInstance().returnCustomers();
        managerView.showCustomers(customers);
    }
    public void generateAccount(Customer customer) {
        BankRepository.getInstance().addAccount(customer);
        Account account = BankRepository.getInstance().returnAccount(customer.getCustomerID());
        if(account != null) {
            managerView.accountApproved(account);
        }
    }
}

package com.cocoding.trackmyspend.service;

import com.cocoding.trackmyspend.domain.Transaction;
import com.cocoding.trackmyspend.domain.User;
import com.cocoding.trackmyspend.domain.accounts.Account;

public class ReportGenerator {
    public void generateReport(User user) {
        System.out.println("-------------MONEY TRACKER REPORT-------------------");
        System.out.println("Report for user: " + user.getName());
        System.out.println("----------------------------------------------------");

        int numberOfAccounts = user.getAccounts().size();

        System.out.println("Number of accounts: " + numberOfAccounts);


        for (Account account : user.getAccounts()) {
            System.out.println("\n----------------------------------------------------");
            System.out.println("Report for account: " + account.getName());
            System.out.println("----------------------------------------------------");
            System.out.println("Balance: " + account.getBalance());

            System.out.println("Number of transactions: " + account.getTransctions().size());
            for (Transaction transaction : account.getTransctions()) {
                System.out.println(transaction);
            }
        }
        // TODO: Implement report generation
    }


}

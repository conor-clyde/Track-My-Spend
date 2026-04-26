package com.cocoding.trackmyspend;

import java.time.LocalDateTime;

import com.cocoding.trackmyspend.domain.Category;
import com.cocoding.trackmyspend.domain.Transaction;
import com.cocoding.trackmyspend.domain.User;
import com.cocoding.trackmyspend.domain.accounts.Account;
import com.cocoding.trackmyspend.domain.accounts.CheckingAccount;
import com.cocoding.trackmyspend.domain.accounts.SavingsAccount;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running OOP mode (no Spring Boot startup)");

        User user = new User("Conor Clyde");
        Account checking = new CheckingAccount("Checking Account", 1200.00);
        Account savings = new SavingsAccount("Savings Account", 4000.00);
        user.addAccount(checking);
        user.addAccount(savings);

        Category food = new Category("food", "Food", Transaction.TransactionType.EXPENSE);
        Category salary = new Category("salary", "Salary", Transaction.TransactionType.INCOME);

        Transaction expense = new Transaction(
                40.00,
                LocalDateTime.now(),
                food,
                "Food");
        Transaction income = new Transaction(
                1200.00,
                LocalDateTime.now(),
                salary,
                "Salary");
        checking.addTransaction(expense);
        checking.addTransaction(income);

        for (Account account : user.getAccounts()) {
            for (Transaction transaction : account.getTransctions()) {
                System.out.println(transaction);
            }
        }
    }
}

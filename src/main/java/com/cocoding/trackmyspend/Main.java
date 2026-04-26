package com.cocoding.trackmyspend;

import java.time.LocalDateTime;

import com.cocoding.trackmyspend.domain.Customer;
import com.cocoding.trackmyspend.domain.Transaction;
import com.cocoding.trackmyspend.domain.accounts.Account;
import com.cocoding.trackmyspend.domain.accounts.CheckingAccount;
import com.cocoding.trackmyspend.domain.accounts.SavingsAccount;
import com.cocoding.trackmyspend.domain.values.ExpenseCategory;
import com.cocoding.trackmyspend.domain.values.IncomeCategory;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running OOP mode (no Spring Boot startup)");

        Customer customer = new Customer("Conor Clyde");
        Account checking = new CheckingAccount("Checking Account", 1200.00);
        Account savings = new SavingsAccount("Savings Account", 4000.00);
        customer.addAccount(checking);
        customer.addAccount(savings);

        Transaction expense = new Transaction(
                40.00,
                LocalDateTime.now(),
                ExpenseCategory.FOOD,
                Transaction.TransactionType.EXPENSE,
                "Food");
        Transaction income = new Transaction(
                1200.00,
                LocalDateTime.now(),
                IncomeCategory.SALARY,
                Transaction.TransactionType.INCOME,
                "Salary");
        checking.addTransaction(expense);
        checking.addTransaction(income);

        for (Account account : customer.getAccounts()) {
            for (Transaction transaction : account.getTransctions()) {
                System.out.println(transaction);
            }
        }
    }
}

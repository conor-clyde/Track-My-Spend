package com.cocoding.trackmyspend;

import com.cocoding.trackmyspend.domain.Transaction;
import com.cocoding.trackmyspend.domain.User;
import com.cocoding.trackmyspend.seed.ConsoleMainSeeder;
import com.cocoding.trackmyspend.service.ReportGenerator;
import com.cocoding.trackmyspend.service.TransactionService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running OOP mode (no Spring Boot startup)");

        User user = ConsoleMainSeeder.seedBasicUser();

        System.err.println("before new transaction");
        ReportGenerator reportGenerator = new ReportGenerator();

        TransactionService transactionService = new TransactionService();
        transactionService.recordTransaction(
                user.getAccounts().get(0),
                60.00,
                user.getCategories().get(0),
                "Groceries",
                Transaction.TransactionType.EXPENSE);
        System.out.println("Transaction recorded: Groceries");
        reportGenerator.generateReport(user);

    }
}

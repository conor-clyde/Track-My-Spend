package com.cocoding.trackmyspend;

import com.cocoding.trackmyspend.domain.Transaction.TransactionType;
import com.cocoding.trackmyspend.domain.User;
import com.cocoding.trackmyspend.seed.ConsoleMainSeeder;
import com.cocoding.trackmyspend.service.ReportGenerator;
import com.cocoding.trackmyspend.service.TransactionException;
import com.cocoding.trackmyspend.service.TransactionService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running OOP mode (no Spring Boot startup)");

        try {
            TransactionService transactionService = new TransactionService();
            User user = ConsoleMainSeeder.seedBasicUser(transactionService);

            ReportGenerator reportGenerator = new ReportGenerator();
            reportGenerator.generateReport(user, transactionService);

            transactionService.createTransaction(
                    TransactionType.INCOME,
                    null,
                    user.getAccounts().get(0).getId(),
                    60.00,
                    "Groceries",
                    user.getCategories().get(0).getName());
        } catch (TransactionException e) {
            System.err.println("Transaction error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }

    }
}

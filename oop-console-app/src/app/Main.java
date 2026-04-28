package app;

import model.User;
import service.ReportGenerator;
import service.TransactionException;
import service.TransactionService;
import util.ConsoleMainSeeder;

public class Main {
    public static void main(String[] args) {
        System.out.println("Running OOP mode");

        try {
            TransactionService transactionService = new TransactionService();
            User user = ConsoleMainSeeder.seedBasicUser();

            ReportGenerator reportGenerator = new ReportGenerator();
            reportGenerator.generateReport(user, transactionService);

            transactionService.recordExpense(
                    user.getAccounts().get(0),
                    60.00,
                    user.getCategories().get(0),
                    "Groceries");
        } catch (TransactionException e) {
            System.err.println("Transaction error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}

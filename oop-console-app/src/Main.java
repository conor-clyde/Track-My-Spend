

import model.Transaction.TransactionType;
import model.User;
import util.ConsoleMainSeeder;
import service.ReportGenerator;
import service.TransactionException;
import service.TransactionService;

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

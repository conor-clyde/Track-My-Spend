package service;

import java.util.List;

import model.Transaction;
import model.User;
import model.accounts.Account;

public class ReportGenerator {
    public void generateReport(User user, TransactionService transactionService) {
        System.out.println("-------------MONEY TRACKER REPORT-------------------");
        System.out.println("Report for user: " + user.getName());
        int numberOfAccounts = user.getAccounts().size();

        System.out.println("Number of accounts: " + numberOfAccounts);

        for (Account account : user.getAccounts()) {
            System.out.println("\n----------------------------------------------------");
            System.out.println("Report for account: " + account.getName());
            System.out.println("----------------------------------------------------");
            List<Transaction> transactions = transactionService.getTransactionsForAccount(account.getId());
            System.out.println("Number of transactions: " + transactions.size());
            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}

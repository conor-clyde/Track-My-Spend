package com.cocoding.trackmyspend.service;

import java.time.LocalDateTime;
import java.util.Objects;

import com.cocoding.trackmyspend.domain.Category;
import com.cocoding.trackmyspend.domain.Transaction;
import com.cocoding.trackmyspend.domain.accounts.Account;

public class TransactionService {
    public Transaction recordExpense(
            Account account,
            double amount,
            Category category,
            String description) {
        return recordExpense(account, amount, category, description, LocalDateTime.now());
    }

    public Transaction recordExpense(
            Account account,
            double amount,
            Category category,
            String description,
            LocalDateTime timestamp) {
        Objects.requireNonNull(account, "account");
        Objects.requireNonNull(category, "category");
        Objects.requireNonNull(description, "description");
        Objects.requireNonNull(timestamp, "timestamp");

        if (!category.isExpenseCategory()) {
            throw new IllegalArgumentException("Expense transactions require an expense category.");
        }

        Transaction transaction = new Transaction(
                amount,
                timestamp,
                category,
                description,
                Transaction.TransactionType.EXPENSE);

        account.changeBalance(-amount);
        account.addTransaction(transaction);
        return transaction;
    }

    public Transaction recordIncome(
            Account account,
            double amount,
            Category category,
            String description) {
        return recordIncome(account, amount, category, description, LocalDateTime.now());
    }

    public Transaction recordIncome(
            Account account,
            double amount,
            Category category,
            String description,
            LocalDateTime timestamp) {
        Objects.requireNonNull(account, "account");
        Objects.requireNonNull(category, "category");
        Objects.requireNonNull(description, "description");
        Objects.requireNonNull(timestamp, "timestamp");

        if (!category.isIncomeCategory()) {
            throw new IllegalArgumentException("Income transactions require an income category.");
        }

        Transaction transaction = new Transaction(
                amount,
                timestamp,
                category,
                description,
                Transaction.TransactionType.INCOME);

        account.changeBalance(amount);
        account.addTransaction(transaction);
        return transaction;
    }

    public void transfer(
            Account fromAccount,
            Account toAccount,
            double amount,
            String description) {
        transfer(fromAccount, toAccount, amount, description, LocalDateTime.now());
    }

    public void transfer(
            Account fromAccount,
            Account toAccount,
            double amount,
            String description,
            LocalDateTime timestamp) {
        Objects.requireNonNull(fromAccount, "fromAccount");
        Objects.requireNonNull(toAccount, "toAccount");
        Objects.requireNonNull(description, "description");
        Objects.requireNonNull(timestamp, "timestamp");

        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
        if (fromAccount == toAccount) {
            throw new IllegalArgumentException("Transfer accounts must be different.");
        }

        fromAccount.changeBalance(-amount);
        toAccount.changeBalance(amount);

        fromAccount.addTransaction(new Transaction(
                amount,
                timestamp,
                "Transfer to " + toAccount.getName() + ": " + description,
                Transaction.TransactionType.TRANSFER));
        toAccount.addTransaction(new Transaction(
                amount,
                timestamp,
                "Transfer from " + fromAccount.getName() + ": " + description,
                Transaction.TransactionType.TRANSFER));
    }
}

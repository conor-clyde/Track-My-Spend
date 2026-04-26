package com.cocoding.trackmyspend.service;

import java.time.LocalDateTime;
import java.util.Objects;

import com.cocoding.trackmyspend.domain.Category;
import com.cocoding.trackmyspend.domain.Transaction;
import com.cocoding.trackmyspend.domain.accounts.Account;

public class TransactionService {
    public Transaction recordTransaction(
            Account account,
            double amount,
            Category category,
            String description,
            Transaction.TransactionType type) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(category, "category");
        if (type == Transaction.TransactionType.TRANSFER) {
            throw new IllegalArgumentException("Use transfer(...) for transfer transactions.");
        }
        validateCategoryForType(category, type);
        LocalDateTime timestamp = LocalDateTime.now();
        int balanceDirection = type == Transaction.TransactionType.EXPENSE ? -1 : 1;
        return recordCategorized(account, amount, category, description, timestamp, type, balanceDirection);
    }

    public void transfer(Account fromAccount, Account toAccount, double amount, String description) {
        LocalDateTime timestamp = LocalDateTime.now();
        validateBasics(amount, description, timestamp);
        Objects.requireNonNull(fromAccount, "fromAccount");
        Objects.requireNonNull(toAccount, "toAccount");
        if (fromAccount == toAccount) {
            throw new IllegalArgumentException("Transfer accounts must be different.");
        }

        fromAccount.changeBalance(-amount);
        toAccount.changeBalance(amount);

        addTransferTransaction(fromAccount, amount, timestamp,
                "Transfer to " + toAccount.getName() + ": " + description,
                Transaction.TransactionType.TRANSFER);
        addTransferTransaction(toAccount, amount, timestamp,
                "Transfer from " + fromAccount.getName() + ": " + description,
                Transaction.TransactionType.TRANSFER);
    }

    private Transaction recordCategorized(
            Account account,
            double amount,
            Category category,
            String description,
            LocalDateTime timestamp,
            Transaction.TransactionType type,
            int balanceDirection) {
        Objects.requireNonNull(account, "account");
        Objects.requireNonNull(category, "category");
        validateBasics(amount, description, timestamp);

        Transaction transaction = new Transaction(amount, timestamp, category, description, type);
        account.changeBalance(balanceDirection * amount);
        account.addTransaction(transaction);
        return transaction;
    }

    private void addTransferTransaction(
            Account account,
            double amount,
            LocalDateTime timestamp,
            String description,
            Transaction.TransactionType type) {
        account.addTransaction(new Transaction(amount, timestamp, description, type));
    }

    private void validateCategoryForType(Category category, Transaction.TransactionType type) {
        if (type == Transaction.TransactionType.EXPENSE && !category.isExpenseCategory()) {
            throw new IllegalArgumentException("Expense transactions require an expense category.");
        }
        if (type == Transaction.TransactionType.INCOME && !category.isIncomeCategory()) {
            throw new IllegalArgumentException("Income transactions require an income category.");
        }
    }

    private void validateBasics(double amount, String description, LocalDateTime timestamp) {
        Objects.requireNonNull(description, "description");
        Objects.requireNonNull(timestamp, "timestamp");
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
    }
}

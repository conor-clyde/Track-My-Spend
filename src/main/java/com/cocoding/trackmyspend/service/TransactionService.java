package com.cocoding.trackmyspend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.cocoding.trackmyspend.domain.Category;
import com.cocoding.trackmyspend.domain.Transaction;
import com.cocoding.trackmyspend.domain.Transaction.TransactionType;

public class TransactionService {
    private final List<Transaction> transactions;

    public TransactionService() {
        this.transactions = new ArrayList<Transaction>();
    }

    public void createTransaction(
            TransactionType type,
            String fromAccountId,
            String toAccountId,
            double amount,
            String description,
            String categoryName) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(description, "description");
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }

        LocalDateTime now = LocalDateTime.now();
        Transaction tx;
        switch (type) {
            case INCOME -> {
                Category category = new Category(categoryName, Category.CategoryType.INCOME, null, null);
                tx = Transaction.income(amount, now, toAccountId, category, description);
            }
            case EXPENSE -> {
                Category category = new Category(categoryName, Category.CategoryType.EXPENSE, null, null);
                tx = Transaction.expense(amount, now, fromAccountId, category, description);
            }
            case TRANSFER -> tx = Transaction.transfer(amount, now, fromAccountId, toAccountId, description);
            default -> throw new IllegalArgumentException("Unsupported transaction type: " + type);
        }

        transactions.add(tx);
    }

    public List<Transaction> getTransactionsForAccount(String accountId) {
        return transactions.stream()
                .filter(transaction ->
                        Objects.equals(transaction.getFromAccountId(), accountId)
                                || Objects.equals(transaction.getToAccountId(), accountId))
                .collect(Collectors.toList());
    }
}

package com.cocoding.trackmyspend.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Transaction {
    public enum TransactionType {
        INCOME,
        EXPENSE
    }

    private final String id;
    private final double amount;
    private final Category category;
    private final LocalDateTime time;
    private final String description;

    public Transaction(
            double amount,
            LocalDateTime timestamp,
            Category category,
            String description) {
        this.id = UUID.randomUUID().toString();
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        this.time = Objects.requireNonNull(timestamp, "timestamp");
        this.category = Objects.requireNonNull(category, "category");
        this.description = Objects.requireNonNull(description, "description");
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return time;
    }

    public Category getCategory() {
        return category;
    }

    public TransactionType getType() {
        return category.isIncomeCategory() ? TransactionType.INCOME : TransactionType.EXPENSE;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction: Amount=" + amount + ", timestamp=" + time + ", category=" + category
                + ", type=" + getType() + ", description=" + description;
    }
}

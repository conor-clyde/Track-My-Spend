package com.cocoding.trackmyspend.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.cocoding.trackmyspend.domain.values.ExpenseCategory;
import com.cocoding.trackmyspend.domain.values.IncomeCategory;
import com.cocoding.trackmyspend.domain.values.TransactionCategory;

public class Transaction {
    public enum TransactionType {
        INCOME,
        EXPENSE
    }

    private final String id;
    private final double amount;
    private final LocalDateTime timestamp;
    private final TransactionCategory category;
    private final TransactionType type;
    private final String description;

    public Transaction(
            double amount,
            LocalDateTime timestamp,
            TransactionCategory category,
            TransactionType type,
            String description) {
        this.id = UUID.randomUUID().toString();
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        this.timestamp = Objects.requireNonNull(timestamp, "timestamp");
        this.category = Objects.requireNonNull(category, "category");
        this.type = Objects.requireNonNull(type, "type");
        this.description = Objects.requireNonNull(description, "description");
        validateCategoryByType(type, category);
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    private void validateCategoryByType(TransactionType type, TransactionCategory category) {
        boolean valid = (type == TransactionType.INCOME && category instanceof IncomeCategory)
                || (type == TransactionType.EXPENSE && category instanceof ExpenseCategory);
        if (!valid) {
            throw new IllegalArgumentException("Category does not match transaction type.");
        }
    }

    @Override
    public String toString() {
        return "Transaction: Amount=" + amount + ", timestamp=" + timestamp + ", category=" + category
                + ", type=" + type + ", description=" + description;
    }
}

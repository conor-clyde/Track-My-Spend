package com.cocoding.trackmyspend.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a financial transaction.
 * Contains an amount, category, timestamp, description, and transaction type.
 */
public class Transaction {
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
    public enum TransactionType {
        INCOME,
        EXPENSE,
        TRANSFER
    }

    private final String id;
    private final double amount;
    private final LocalDateTime time;
    private final String description;
    private final TransactionType type;
    private final Category category;
    

    public Transaction(double amount, LocalDateTime timestamp, Category category, String description, TransactionType transactionType) {
        this(amount, timestamp, description, transactionType, category);
    }

    public Transaction(double amount, LocalDateTime timestamp, String description, TransactionType transactionType) {
        this(amount, timestamp, description, transactionType, null);
    }

    private Transaction(
            double amount,
            LocalDateTime timestamp,
            String description,
            TransactionType type,
            Category category) {
        this.id = UUID.randomUUID().toString();
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        this.type = Objects.requireNonNull(type, "type");
        this.time = Objects.requireNonNull(timestamp, "timestamp");
        this.description = Objects.requireNonNull(description, "description");
        this.category = category;
        validateCategoryType(type, category);
        this.amount = amount;
    }

    private void validateCategoryType(TransactionType type, Category category) {
        if (type == TransactionType.TRANSFER) {
            return;
        }
        if (category == null) {
            throw new IllegalArgumentException("Income/expense transactions require a category.");
        }
        if (type == TransactionType.INCOME && !category.isIncomeCategory()) {
            throw new IllegalArgumentException("Income transaction must use an income category.");
        }
        if (type == TransactionType.EXPENSE && !category.isExpenseCategory()) {
            throw new IllegalArgumentException("Expense transaction must use an expense category.");
        }
        // TRANSFER can be represented as either outgoing (expense) or incoming (income).
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

    public TransactionType getTransactionType() {
        return type;
    }


    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        String categoryLabel = category == null ? "TRANSFER" : category.toString();
        return "Transaction: Amount=" + amount + ", Date=" + DISPLAY_FORMAT.format(time) + ", category=" + categoryLabel
                + ", description=" + description + ", type=" + type;
    }
}


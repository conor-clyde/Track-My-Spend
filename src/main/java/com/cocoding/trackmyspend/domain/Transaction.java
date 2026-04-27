package com.cocoding.trackmyspend.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * Transaction-centric model:
 * - INCOME:   toAccountId is set, fromAccountId is null
 * - EXPENSE:  fromAccountId is set, toAccountId is null
 * - TRANSFER: both fromAccountId and toAccountId are set
 */
public class Transaction {
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    public enum TransactionType {
        INCOME,
        EXPENSE,
        TRANSFER
    }

    private final String id;
    private  double amount;
    private  LocalDateTime timestamp;
    private  String description;
    private  TransactionType type;
    private  Category category;
    private  String fromAccountId;
    private  String toAccountId;
    

    public Transaction() {
        this.id = UUID.randomUUID().toString();
        this.amount = 0;
        this.timestamp = LocalDateTime.now();
        this.description = "";
        this.type = TransactionType.INCOME;
        this.category = null;
        this.fromAccountId = null;
        this.toAccountId = null;
    }

    public static Transaction income(
            double amount,
            LocalDateTime timestamp,
            String toAccountId,
            Category category,
            String description) {
        return new Transaction(amount, timestamp, description, TransactionType.INCOME, category, null, toAccountId);
    }

    public static Transaction expense(
            double amount,
            LocalDateTime timestamp,
            String fromAccountId,
            Category category,
            String description) {
        return new Transaction(amount, timestamp, description, TransactionType.EXPENSE, category, fromAccountId, null);
    }

    public static Transaction transfer(
            double amount,
            LocalDateTime timestamp,
            String fromAccountId,
            String toAccountId,
            String description) {
        return new Transaction(amount, timestamp, description, TransactionType.TRANSFER, null, fromAccountId, toAccountId);
    }

    private Transaction(
            double amount,
            LocalDateTime timestamp,
            String description,
            TransactionType type,
            Category category,
            String fromAccountId,
            String toAccountId) {
        this.id = UUID.randomUUID().toString();
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        this.type = Objects.requireNonNull(type, "type");
        this.timestamp = Objects.requireNonNull(timestamp, "timestamp");
        this.description = Objects.requireNonNull(description, "description");
        this.category = category;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        validateShape(type, category, fromAccountId, toAccountId);
        this.amount = amount;
    }

    private void validateShape(
            TransactionType type,
            Category category,
            String fromAccountId,
            String toAccountId) {
        if (type == TransactionType.TRANSFER) {
            if (fromAccountId == null || toAccountId == null) {
                throw new IllegalArgumentException("Transfer transactions require fromAccountId and toAccountId.");
            }
            if (fromAccountId.equals(toAccountId)) {
                throw new IllegalArgumentException("Transfer accounts must be different.");
            }
            if (category != null) {
                throw new IllegalArgumentException("Transfer transactions do not use categories.");
            }
            return;
        }

        if (category == null) {
            throw new IllegalArgumentException("Income/expense transactions require a category.");
        }
        if (type == TransactionType.INCOME && toAccountId == null) {
            throw new IllegalArgumentException("Income transactions require toAccountId.");
        }
        if (type == TransactionType.EXPENSE && fromAccountId == null) {
            throw new IllegalArgumentException("Expense transactions require fromAccountId.");
        }
        if (type == TransactionType.INCOME && fromAccountId != null) {
            throw new IllegalArgumentException("Income transactions cannot set fromAccountId.");
        }
        if (type == TransactionType.EXPENSE && toAccountId != null) {
            throw new IllegalArgumentException("Expense transactions cannot set toAccountId.");
        }
        if (type == TransactionType.INCOME && !category.isIncomeCategory()) {
            throw new IllegalArgumentException("Income transaction must use an income category.");
        }
        if (type == TransactionType.EXPENSE && !category.isExpenseCategory()) {
            throw new IllegalArgumentException("Expense transaction must use an expense category.");
        }
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

     public void setType(TransactionType type) {
        this.type = Objects.requireNonNull(type, "type");
     }



    public void setCategory(Category category) {
        this.category = Objects.requireNonNull(category, "category");
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Category getCategory() {
        return category;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public TransactionType getTransactionType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        if (type == TransactionType.TRANSFER) {
            return "Transaction: Amount=" + amount
                    + ", Date=" + DISPLAY_FORMAT.format(timestamp)
                    + ", fromAccountId=" + fromAccountId
                    + ", toAccountId=" + toAccountId
                    + ", description=" + description
                    + ", type=" + type;
        }
        return "Transaction: Amount=" + amount
                + ", Date=" + DISPLAY_FORMAT.format(timestamp)
                + ", fromAccountId=" + fromAccountId
                + ", toAccountId=" + toAccountId
                + ", category=" + category
                + ", description=" + description
                + ", type=" + type;
    }


}


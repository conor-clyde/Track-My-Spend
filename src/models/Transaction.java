package models;

import java.time.LocalDateTime;
import java.util.UUID;

import values.Categories;

public class Transaction {
    public enum TransactionType {
        INCOME,
        EXPENSE
    }

    private final String id;
    private final double amount;
    private final LocalDateTime timestamp;
    private final Categories category;
    private final TransactionType type;

    public Transaction(double amount, LocalDateTime timestamp, Categories category, TransactionType type) {
        this.id = UUID.randomUUID().toString();
        this.amount = amount;
        this.timestamp = timestamp;
        this.category = category;
        this.type = type;
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

    public Categories getCategory() {
        return category;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction: Amount=" + amount + ", timestamp=" + timestamp + ", category=" + category
                + ", type=" + type;
    }
}

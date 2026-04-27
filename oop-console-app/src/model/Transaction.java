package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Transaction-centric model:
 * - INCOME: toAccountId is set, fromAccountId is null
 * - EXPENSE: fromAccountId is set, toAccountId is null
 * - TRANSFER: both fromAccountId and toAccountId are set
 */
public class Transaction {
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");

    public enum TransactionType {
        INCOME,
        EXPENSE,
        TRANSFER
    }

    private final double amount;
    private final LocalDateTime timestamp;
    private final String description;
    private final TransactionType type;
    private final Category category;
    private final String fromAccountId;
    private final String toAccountId;

    public static Transaction income(double amount, LocalDateTime timestamp, String toAccountId, Category category,
            String description) {
        return new Transaction(amount, timestamp, description, TransactionType.INCOME, category, null, toAccountId);
    }

    public static Transaction expense(double amount, LocalDateTime timestamp, String fromAccountId, Category category,
            String description) {
        return new Transaction(amount, timestamp, description, TransactionType.EXPENSE, category, fromAccountId, null);
    }

    public static Transaction transfer(double amount, LocalDateTime timestamp, String fromAccountId, String toAccountId,
            String description) {
        return new Transaction(amount, timestamp, description, TransactionType.TRANSFER, null, fromAccountId,
                toAccountId);
    }

    private Transaction(double amount, LocalDateTime timestamp, String description, TransactionType type,
            Category category, String fromAccountId, String toAccountId) {
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

    private void validateShape( TransactionType type, Category category,String fromAccountId, String toAccountId) {
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

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
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

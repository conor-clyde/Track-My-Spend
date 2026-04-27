package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Category;
import model.Transaction;
import model.Transaction.TransactionType;

public class TransactionService {
    private final List<Transaction> transactions;

    public TransactionService() {
        this.transactions = new ArrayList<Transaction>();
    }

    public void createTransaction(TransactionType type, String fromAccountId, String toAccountId, double amount, String description, String categoryName) {
        validateCommon(type, amount, description);
        validateByType(type, fromAccountId, toAccountId, categoryName);

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
            default -> throw new TransactionException("Unsupported transaction type: " + type);
        }

        transactions.add(tx);
    }

    public List<Transaction> getTransactionsForAccount(String accountId) {
        if (accountId == null || accountId.isBlank()) {
            throw new TransactionException("Account ID is required.");
        }
        return transactions.stream()
                .filter(transaction ->
                        accountId.equals(transaction.getFromAccountId())
                                || accountId.equals(transaction.getToAccountId()))
                .collect(Collectors.toList());
    }

    private void validateCommon(TransactionType type, double amount, String description) {
        if (type == null) {
            throw new TransactionException("Transaction type is required.");
        }
        if (amount <= 0) {
            throw new TransactionException("Amount must be positive.");
        }
        if (description == null || description.isBlank()) {
            throw new TransactionException("Description is required.");
        }
    }

    private void validateByType(TransactionType type, String fromAccountId, String toAccountId, String categoryName) {
        if (type == TransactionType.INCOME) {
            if (toAccountId == null || toAccountId.isBlank()) {
                throw new TransactionException("Income requires toAccountId.");
            }
            if (categoryName == null || categoryName.isBlank()) {
                throw new TransactionException("Income requires category.");
            }
        } else if (type == TransactionType.EXPENSE) {
            if (fromAccountId == null || fromAccountId.isBlank()) {
                throw new TransactionException("Expense requires fromAccountId.");
            }
            if (categoryName == null || categoryName.isBlank()) {
                throw new TransactionException("Expense requires category.");
            }
        } else if (type == TransactionType.TRANSFER) {
            if (fromAccountId == null || fromAccountId.isBlank() || toAccountId == null || toAccountId.isBlank()) {
                throw new TransactionException("Transfer requires fromAccountId and toAccountId.");
            }
            if (fromAccountId.equals(toAccountId)) {
                throw new TransactionException("Transfer accounts must be different.");
            }
        }
    }
}

package service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Transaction;
import model.Transaction.TransactionType;
import model.accounts.Account;

public class TransactionService {
    private final List<Transaction> transactions;

    public TransactionService() {
        this.transactions = new ArrayList<>();
    }

    public void recordIncome(Account toAccount, double amount, Category category, String description) {
        validateAmountAndDescription(amount, description);
        if (toAccount == null) {
            throw new TransactionException("Income requires a destination account.");
        }
        if (category == null || !category.isIncomeCategory()) {
            throw new TransactionException("Income requires an income category.");
        }
        toAccount.deposit(amount);
        transactions.add(Transaction.income(amount, LocalDateTime.now(), toAccount.getId(), category, description));
    }

    public void recordExpense(Account fromAccount, double amount, Category category, String description) {
        validateAmountAndDescription(amount, description);
        if (fromAccount == null) {
            throw new TransactionException("Expense requires a source account.");
        }
        if (category == null || !category.isExpenseCategory()) {
            throw new TransactionException("Expense requires an expense category.");
        }
        fromAccount.withdraw(amount);
        transactions.add(Transaction.expense(amount, LocalDateTime.now(), fromAccount.getId(), category, description));
    }

    public void transfer(Account fromAccount, Account toAccount, double amount, String description) {
        validateAmountAndDescription(amount, description);
        if (fromAccount == null || toAccount == null) {
            throw new TransactionException("Transfer requires from and to accounts.");
        }
        if (fromAccount.getId().equals(toAccount.getId())) {
            throw new TransactionException("Transfer accounts must be different.");
        }
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
        transactions.add(
                Transaction.transfer(amount, LocalDateTime.now(), fromAccount.getId(), toAccount.getId(), description));
    }

    public List<Transaction> getTransactionsForAccount(String accountId) {
        if (accountId == null || accountId.isBlank()) {
            throw new TransactionException("Account ID is required.");
        }
        return transactions.stream()
                .filter(transaction -> accountId.equals(transaction.getFromAccountId())
                        || accountId.equals(transaction.getToAccountId()))
                .toList();
    }

    private void validateAmountAndDescription(double amount, String description) {
        if (amount <= 0) {
            throw new TransactionException("Amount must be positive.");
        }
        if (description == null || description.isBlank()) {
            throw new TransactionException("Description is required.");
        }
    }
}

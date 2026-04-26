package com.cocoding.trackmyspend.seed;

import java.time.LocalDateTime;
import java.time.YearMonth;

import com.cocoding.trackmyspend.domain.Budget;
import com.cocoding.trackmyspend.domain.Category;
import com.cocoding.trackmyspend.domain.Category.CategoryType;
import com.cocoding.trackmyspend.domain.Transaction;
import com.cocoding.trackmyspend.domain.User;
import com.cocoding.trackmyspend.domain.accounts.Account;
import com.cocoding.trackmyspend.domain.accounts.CashAccount;
import com.cocoding.trackmyspend.domain.accounts.CheckingAccount;
import com.cocoding.trackmyspend.domain.accounts.SavingsAccount;

/**
 * Builds in-memory sample data for console testing.
 */
public final class ConsoleMainSeeder {

    private ConsoleMainSeeder() {
    }

    public static User seedBasicUser() {
        User user = new User("Conor Clyde");

        Account checking = addAccount(user, new CheckingAccount("Checking", 2000.00));
        Account savings = addAccount(user, new SavingsAccount("Savings", 8000.00));
        Account cash = addAccount(user, new CashAccount("Cash", 80.00));

        Category groceries = addCategory(user, "Groceries", CategoryType.EXPENSE);
        Category pet = addCategory(user, "Pet", CategoryType.EXPENSE);
        Category salary = addCategory(user, "Salary", CategoryType.INCOME);
        Category rent = addCategory(user, "Rent", CategoryType.EXPENSE);
        Category transport = addCategory(user, "Transport", CategoryType.EXPENSE);
        Category dining = addCategory(user, "Dining", CategoryType.EXPENSE);

        YearMonth currentMonth = YearMonth.now();
        Budget groceriesBudget = new Budget(groceries, YearMonth.now(), 400.00, user);
        Budget petBudget = new Budget(pet, currentMonth, 150.00, user);
        user.addBudget(groceriesBudget);
        user.addBudget(petBudget);

        LocalDateTime now = LocalDateTime.now();
        addTransaction(checking, 3200.00, now.minusDays(12), salary, "Monthly salary", Transaction.TransactionType.INCOME);
        addTransaction(checking, 1450.00, now.minusDays(10), rent, "Rent payment", Transaction.TransactionType.EXPENSE);
        addTransaction(checking, 95.00, now.minusDays(7), groceries, "Weekly groceries", Transaction.TransactionType.EXPENSE);
        addTransaction(checking, 65.00, now.minusDays(4), transport, "Metro card top-up", Transaction.TransactionType.EXPENSE);
        addTransaction(checking, 42.00, now.minusDays(2), dining, "Dinner with friends", Transaction.TransactionType.EXPENSE);
        addTransaction(cash, 28.00, now.minusDays(1), pet, "Cat food", Transaction.TransactionType.EXPENSE);
        addTransfer(checking, savings, 500.00, now.minusDays(3), "Move money to savings");

        return user;
    }

    private static Account addAccount(User user, Account account) {
        user.addAccount(account);
        return account;
    }

    private static Category addCategory(User user, String name, CategoryType type) {
        Category category = new Category(name, type, null, user);
        user.addCategory(category);
        return category;
    }

    private static void addTransaction(
            Account account,
            double amount,
            LocalDateTime timestamp,
            Category category,
            String description,
            Transaction.TransactionType transactionType) {
        account.addTransaction(new Transaction(amount, timestamp, category, description, transactionType));
    }

    private static void addTransfer(
            Account fromAccount,
            Account toAccount,
            double amount,
            LocalDateTime timestamp,
            String description) {
        fromAccount.changeBalance(-amount);
        toAccount.changeBalance(amount);

        fromAccount.addTransaction(new Transaction(amount, timestamp, description, Transaction.TransactionType.TRANSFER));
        toAccount.addTransaction(new Transaction(amount, timestamp, description, Transaction.TransactionType.TRANSFER));
    }
}

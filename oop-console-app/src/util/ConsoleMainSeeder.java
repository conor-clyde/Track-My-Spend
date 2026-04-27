package util;

import java.time.YearMonth;

import model.Budget;
import model.Category;
import model.Category.CategoryType;
import model.Transaction;
import model.User;
import model.accounts.Account;
import model.accounts.CashAccount;
import model.accounts.CheckingAccount;
import model.accounts.SavingsAccount;
import service.TransactionService;

/**
 * Builds in-memory sample data for console testing.
 */
public final class ConsoleMainSeeder {

    private ConsoleMainSeeder() {
    }

    public static User seedBasicUser(TransactionService transactionService) {
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

        addTransaction(transactionService, checking, 3200.00, salary, "Monthly salary", Transaction.TransactionType.INCOME);
        addTransaction(transactionService, checking, 1450.00, rent, "Rent payment", Transaction.TransactionType.EXPENSE);
        addTransaction(transactionService, checking, 95.00, groceries, "Weekly groceries", Transaction.TransactionType.EXPENSE);
        addTransaction(transactionService, checking, 65.00, transport, "Metro card top-up", Transaction.TransactionType.EXPENSE);
        addTransaction(transactionService, checking, 42.00, dining, "Dinner with friends", Transaction.TransactionType.EXPENSE);
        addTransaction(transactionService, cash, 28.00, pet, "Cat food", Transaction.TransactionType.EXPENSE);
        addTransfer(transactionService, checking, savings, 500.00, "Move money to savings");

        return user;
    }

    private static Account addAccount(User user, Account account) {
        user.addAccount(account);
        return account;
    }

    private static Category addCategory(User user, String name, CategoryType type) {
        Category category = new Category(name, type);
        user.addCategory(category);
        return category;
    }

    private static void addTransaction(
            TransactionService transactionService,
            Account account,
            double amount,
            Category category,
            String description,
            Transaction.TransactionType transactionType) {
        if (transactionType == Transaction.TransactionType.INCOME) {
            transactionService.recordIncome(account, amount, category, description);
        } else if (transactionType == Transaction.TransactionType.EXPENSE) {
            transactionService.recordExpense(account, amount, category, description);
        } else {
            throw new IllegalArgumentException("Use addTransfer for transfer transactions.");
        }
    }

    private static void addTransfer(
            TransactionService transactionService,
            Account fromAccount,
            Account toAccount,
            double amount,
            String description) {
        transactionService.transfer(fromAccount, toAccount, amount, description);
    }
}

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
    static TransactionService transactionService = new TransactionService();
    private ConsoleMainSeeder() {
    }

    public static User seedBasicUser() {
    
        User user = new User("Conor Clyde");
    

        Account checking = addAccount(user, new CheckingAccount("Checking", 2000.00));
        Account savings = addAccount(user, new SavingsAccount("Savings", 8000.00));
        Account cash = addAccount(user, new CashAccount("Cash", 80.00));

        Category groceries = addCategory(new Category("Groceries", CategoryType.EXPENSE), user);
        Category pet = addCategory(new Category("Pet", CategoryType.EXPENSE), user);
        Category salary = addCategory(new Category("Salary", CategoryType.INCOME), user);
        Category rent = addCategory(new Category("Rent", CategoryType.EXPENSE), user);
        Category transport = addCategory(new Category("Transport", CategoryType.EXPENSE), user);
        Category dining = addCategory(new Category("Dining", CategoryType.EXPENSE), user);

        Budget groceriesBudget = addBudget(new Budget(groceries, 400.00), user);
        Budget petBudget = addBudget(new Budget(pet, 150.00), user);

        addTransaction( checking, 3200.00, salary, "Monthly salary",
                Transaction.TransactionType.INCOME);
        addTransaction( checking, 1000.00, rent, "Rent payment",
                Transaction.TransactionType.EXPENSE);
        addTransaction( checking, 95.00, groceries, "Weekly groceries",
                Transaction.TransactionType.EXPENSE);
        addTransaction( checking, 65.00, transport, "Metro card top-up",
                Transaction.TransactionType.EXPENSE);
        addTransaction( checking, 42.00, dining, "Dinner with friends",
                Transaction.TransactionType.EXPENSE);
        addTransaction(cash, 28.00, pet, "Cat food", Transaction.TransactionType.EXPENSE);
        addTransfer(checking, savings, 500.00, "Move money to savings");

        return user;
    }

    private static Account addAccount(User user, Account account) {
        user.addAccount(account);
        return account;
    }

    private static Category addCategory(Category category, User user) {
        user.addCategory(category);
        return category;
    }

    private static Budget addBudget(Budget budget, User user) {
        user.addBudget(budget);
        return budget;
    }

    private static void addTransaction(Account account, double amount, Category category, String description, Transaction.TransactionType transactionType) {
             
        if (transactionType == Transaction.TransactionType.INCOME) {
            transactionService.recordIncome(account, amount, category, description);
        } else if (transactionType == Transaction.TransactionType.EXPENSE) {
            transactionService.recordExpense(account, amount, category, description);
        } else {
            throw new IllegalArgumentException("Use addTransfer for transfer transactions.");
        }
    }

    private static void addTransfer(Account fromAccount, Account toAccount,
            double amount, String description) {
        transactionService.transfer(fromAccount, toAccount, amount, description);
    }
}

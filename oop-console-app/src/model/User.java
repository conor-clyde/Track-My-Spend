package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import model.accounts.Account;

/**
 * Represents a user of the application.
 * Contains accounts, budgets, and categories.
 */
public class User {
    private final String name;
    private final List<Account> accounts;
    private final List<Budget> budgets;
    private final List<Category> categories;

    public User(String name) {
        this.name = Objects.requireNonNull(name, "name");
        this.accounts = new ArrayList<>();
        this.budgets = new ArrayList<>();
        this.categories = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    @Override
    public String toString() {
        return "User: Name=" + name + ", accounts=" + accounts + ", budgets=" + budgets + ", categories=" + categories;
    }
}

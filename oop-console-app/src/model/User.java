package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import model.accounts.Account;

/**
 * Represents a user of the application.
 * Contains a list of accounts, budgets, and categories.
 */
public class User {
    private final String id;
    private String name;
    private final List<Account> accounts;
    private final List<Budget> budgets;
    private final List<Category> categories;

    // Constructor
    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = Objects.requireNonNull(name, "name");
        this.accounts = new ArrayList<Account>();
        this.budgets = new ArrayList<Budget>();
        this.categories = new ArrayList<Category>();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public List<Budget> getBudgets() {
        return Collections.unmodifiableList(budgets);
    }

    public void addBudget(Budget budget) {
        budgets.add(budget);
    }

    public void removeBudget(Budget budget) {
        budgets.remove(budget);
    }

    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void removeCategory(Category category) {
        categories.remove(category);
    }

    // toString method
    @Override
    public String toString() {
        return "User: Name=" + name + ", accounts=" + accounts.toString() + ", budgets=" + budgets.toString() + ", categories=" + categories.toString();
    }
}

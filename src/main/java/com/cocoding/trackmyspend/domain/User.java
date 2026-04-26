package com.cocoding.trackmyspend.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.cocoding.trackmyspend.domain.accounts.Account;
/**
 * Owns accounts; list is defensively copied (encapsulation).
 */
public class User {
    private final String id;
    private String name;
    private List<Account> accounts;
    private List<Budget> budgets;
    private List<Category> categories;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = Objects.requireNonNull(name, "name");

    }

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
        if (accounts == null) {
            accounts = new ArrayList<Account>();
        }
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }
    public List<Budget> getBudgets() {
        return Collections.unmodifiableList(budgets);
    }

    public void addBudget(Budget budget) {
        if (budgets == null) {
            budgets = new ArrayList<Budget>();
        }
        budgets.add(budget);
    }
    public List<Category> getCategories() {
        return Collections.unmodifiableList(categories);
    }

    public void addCategory(Category category) {
        if (categories == null) {
            categories = new ArrayList<Category>();
        }
        categories.add(category);
    }

    @Override
    public String toString() {
        return "User: Name=" + name + ", accounts=" + accounts.toString() + "]";
    }
}

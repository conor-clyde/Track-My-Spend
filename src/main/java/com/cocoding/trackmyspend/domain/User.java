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
    private List<Category> categories;
    private List<Budget> budgets;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = Objects.requireNonNull(name, "name");
        this.accounts = new ArrayList<Account>();
        this.categories = new ArrayList<Category>();
        this.budgets = new ArrayList<Budget>();
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
        accounts.add(account);
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

    @Override
    public String toString() {
        return "User: Name=" + name + ", accounts=" +
                accounts.toString() + ", categories=" + categories.toString() + ", budgets=" + budgets.toString() + "]";
    }
}

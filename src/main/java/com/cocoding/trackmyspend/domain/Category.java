package com.cocoding.trackmyspend.domain;

import java.util.Objects;
import java.util.UUID;

public class Category {
    public enum CategoryType {
        INCOME,
        EXPENSE
    }

    private final String id;
    private final String name;
    private final CategoryType type;
    private final Category parent;
    private final User user;

    public Category(String name, CategoryType type, Category parent, User user) {
        this.id = UUID.randomUUID().toString();
        this.name = Objects.requireNonNull(name, "name");
        this.type = Objects.requireNonNull(type, "type");
        this.parent = parent;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryType getType() {
        return type;
    }

    public Category getParent() {
        return parent;
    }

    public User getUser() {
        return user;
    }

    public boolean isExpenseCategory() {
        return type == CategoryType.EXPENSE;
    }

    public boolean isIncomeCategory() {
        return type == CategoryType.INCOME;
    }

    @Override
    public String toString() {
        return name;
    }
}

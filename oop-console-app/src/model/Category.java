package model;

import java.util.Objects;

public class Category {
    public enum CategoryType {
        INCOME,
        EXPENSE
    }

    private final String name;
    private final CategoryType type;

    public Category(String name, CategoryType type) {
        this.name = Objects.requireNonNull(name, "name");
        this.type = Objects.requireNonNull(type, "type");
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

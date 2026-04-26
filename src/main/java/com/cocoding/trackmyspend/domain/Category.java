package com.cocoding.trackmyspend.domain;

public class Category {
    
    private final String id;
    private final String name;
    private final Transaction.TransactionType type;
    private final Category parent;
    private final User user;
    

    public Category(String id, String name, Transaction.TransactionType type) {
        this(id, name, type, null, null);
    }

    public Category(String id, String name, Transaction.TransactionType type, Category parent, User user) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.parent = parent;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Transaction.TransactionType getType() {
        return type;
    }

    public Category getParent() {
        return parent;
    }

    public User getUser() {
        return user;
    }

    public boolean isExpenseCategory() {
        return type == Transaction.TransactionType.EXPENSE;
    }

    public boolean isIncomeCategory() {
        return type == Transaction.TransactionType.INCOME;
    }
}

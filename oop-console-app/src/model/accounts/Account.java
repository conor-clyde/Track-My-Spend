package model.accounts;

import java.util.UUID;

/**
 * Simple account model. Transactions are stored centrally in
 * TransactionService.
 */
public abstract class Account {
    private final String id;
    private String name;
    private double balance;

    public Account(String name, double openingBalance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = openingBalance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (!canWithdraw(amount)) {
            throw new IllegalArgumentException("Insufficient funds for this account.");
        }
        this.balance -= amount;
    }

    protected boolean canWithdraw(double amount) {
        return balance >= amount;
    }

    @Override
    public String toString() {
        return "Account: Name=" + name + ", balance=" + balance;
    }
}

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
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account: Name=" + name + ", balance=" + balance;
    }
}

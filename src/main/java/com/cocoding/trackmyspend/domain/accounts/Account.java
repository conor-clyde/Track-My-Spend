package com.cocoding.trackmyspend.domain.accounts;

import java.util.UUID;

/**
 * Simple account model. Transactions are stored centrally in TransactionService.
 */
public abstract class Account {
    private final String id;
    private String name;
    private final double openingBalance;

    public Account(String name, double openingBalance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.openingBalance = openingBalance;
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

    public double getOpeningBalance() {
        return openingBalance;
    }

    @Override
    public String toString() {
        return "Account: Name=" + name + ", openingBalance=" + openingBalance;
    }
}

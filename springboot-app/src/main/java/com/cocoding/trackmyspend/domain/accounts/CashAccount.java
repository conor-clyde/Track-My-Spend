package com.cocoding.trackmyspend.domain.accounts;

public class CashAccount extends Account {
    public CashAccount(String name, double openingBalance) {
        super(name, openingBalance);
    }

    @Override
    public String toString() {
        return "CashAccount [id=" + getId() + ", name=" + getName() + ", balance=" + getBalance() + "]";
    }
}

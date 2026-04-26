package com.cocoding.trackmyspend.domain.accounts;

public class CashAccount extends Account {
    public CashAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public String toString() {
        return "CashAccount [id=" + getId() + ", name=" + getName() + ", balance=" + getBalance() + ", transctions=" + getTransctions().toString() + "]";
    }

    @Override
    public void deposit(double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deposit'");
    }

    @Override
    public void withdraw(double amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdraw'");
    }
}

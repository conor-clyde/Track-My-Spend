package com.cocoding.trackmyspend.domain.accounts;

public class DebtAccount extends Account {
    public DebtAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public String toString() {
        return "DebtAccount [id=" + getId() + ", name=" + getName() + ", balance=" + getBalance() + ", transctions=" + getTransctions().toString() + "]";
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

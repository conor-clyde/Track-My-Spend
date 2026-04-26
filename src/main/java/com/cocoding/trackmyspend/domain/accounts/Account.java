package com.cocoding.trackmyspend.domain.accounts;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cocoding.trackmyspend.domain.Transaction;

public abstract class Account {
    private final String id;
    private String name;
    private double balance;
    private List<Transaction> transctions;

    public Account(String name, double balance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = balance;
        this.transctions = new ArrayList<Transaction>();
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

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

    public void changeBalance(double amount) {
        this.balance += amount;
    }

    public List<Transaction> getTransctions() {
        return transctions;
    }

    public void addTransaction(Transaction transaction) {
        this.transctions.add(transaction);
    }

    @Override
    public String toString() {
        return "Account: Name=" + name + ", balance=" + balance + ", transctions=" + transctions.toString() + "]";
    }
}

package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
    private final String id;
    private String name;
    private double balance;
    private List<Transaction> transctions;

    public Account(String name, double balance) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = balance;
        this.transctions = new ArrayList<>();
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

    public List<Transaction> getTransctions() {
        return transctions;
    }

    public void addTransaction(Transaction transaction) {
        this.transctions.add(transaction);
    }

}

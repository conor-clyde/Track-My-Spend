package models;

import java.time.LocalDateTime;

import values.Categories;

public abstract class Transaction {

    private final String id;
    private final double amount;
    private final LocalDateTime timestamp;
    private Categories category;

    public Transaction(String id, double amount, LocalDateTime timestamp, Categories salary) {
        this.id = id;
        this.amount = amount;
        this.timestamp = timestamp;
        this.category = salary;
    }

    

}

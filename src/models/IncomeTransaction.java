package models;

import java.time.LocalDateTime;

import values.Categories;

public class IncomeTransaction extends Transaction {

    public IncomeTransaction(String id, double amount, LocalDateTime timestamp, Categories salary) {
        super(id, amount, timestamp, salary);
        System.out.println("!Income Transaction created!");
    }
    
}
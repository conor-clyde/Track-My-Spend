package models;

import java.time.LocalDateTime;

import values.Categories;

public class ExpenseTransaction extends Transaction {

    public ExpenseTransaction(String id, double amount, LocalDateTime timestamp, Categories food) {
        super(id, amount, timestamp, food);
        System.out.println("!Expense Transaction created!");
    }
    
}
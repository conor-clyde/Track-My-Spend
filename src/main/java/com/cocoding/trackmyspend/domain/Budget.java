package com.cocoding.trackmyspend.domain;

import java.time.YearMonth;

public class Budget {
    private final String id;
    private final Category category;
    private final YearMonth month;
    private final double limit;
    private final User user;

    public Budget(String id, Category category, YearMonth month, double limit, User user) {
        this.id = id;
        this.category = category;
        this.month = month;
        this.limit = limit;
        this.user = user;
    }
    
    
}

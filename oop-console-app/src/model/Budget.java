package model;

import java.time.YearMonth;
import java.util.UUID;

public class Budget {
    private final String id;
    private Category category;
    private YearMonth month;
    private double limit;
    private final User user;

    public Budget(Category category, YearMonth month, double limit, User user) {
        this.id = UUID.randomUUID().toString();
        this.category = category;
        this.month = month;
        this.limit = limit;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public User getUser() {
        return user;
    }
    
    @Override
    public String toString() {
        return "Budget: category=" + category + ", month=" + month + ", limit=" + limit + ", user=" + user;
    }
}

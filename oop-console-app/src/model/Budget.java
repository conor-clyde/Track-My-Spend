package model;

import java.time.YearMonth;

public class Budget {
    private final Category category;
    private final YearMonth month;
    private final double limit;
    private final User user;

    public Budget(Category category, YearMonth month, double limit, User user) {
        this.category = category;
        this.month = month;
        this.limit = limit;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Budget: category=" + category + ", month=" + month + ", limit=" + limit + ", user=" + user;
    }
}

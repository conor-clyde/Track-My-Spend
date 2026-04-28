package model;

public class Budget {
    private final Category category;
    private final double limit;

    public Budget(Category category, double limit) {
        this.category = category;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Budget: category=" + category + ", limit=" + limit;
    }
}

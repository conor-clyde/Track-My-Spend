package model.accounts;

public class CheckingAccount extends Account {
    private static final double OVERDRAFT_LIMIT = -500.0;

    public CheckingAccount(String name, double openingBalance) {
        super(name, openingBalance);
    }

    @Override
    protected boolean canWithdraw(double amount) {
        return getBalance() - amount >= OVERDRAFT_LIMIT;
    }

    @Override
    public String toString() {
        return "CheckingAccount [id=" + getId() + ", name=" + getName() + ", balance=" + getBalance() + "]";
    }
}

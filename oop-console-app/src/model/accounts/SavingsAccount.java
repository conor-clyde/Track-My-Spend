package model.accounts;

public class SavingsAccount extends Account {
    public SavingsAccount(String name, double openingBalance) {
        super(name, openingBalance);
    }

    @Override
    public String toString() {
        return "SavingsAccount [id=" + getId() + ", name=" + getName() + ", balance=" + getBalance() + "]";
    }
}

package model.accounts;

public class CheckingAccount extends Account {
    public CheckingAccount(String name, double openingBalance) {
        super(name, openingBalance);
    }

    @Override
    public String toString() {
        return "CheckingAccount [id=" + getId() + ", name=" + getName() + ", balance=" + getBalance() + "]";
    }
}

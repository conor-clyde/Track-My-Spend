package models;

public class SavingsAccount extends Account {
    public SavingsAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public String toString() {
        return "SavingsAccount [id=" + getId() + ", name=" + getName() + ", balance=" + getBalance() + ", transctions=" + getTransctions().toString() + "]";
    }
}

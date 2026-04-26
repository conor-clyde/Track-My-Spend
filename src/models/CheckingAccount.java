package models;

public class CheckingAccount extends Account {
    public CheckingAccount(String name, double balance) {
        super(name, balance);
    }

    @Override
    public String toString() {
        return "CheckingAccount [id=" + getId() + ", name=" + getName() + ", balance=" + getBalance() + ", transctions=" + getTransctions().toString() + "]";
    }
}

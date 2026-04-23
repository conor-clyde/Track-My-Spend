package banking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Facade for customers and cross-account operations (cohesion).
 */
public class Bank {
    private final String name;
    private final List<Customer> customers = new ArrayList<>();

    public Bank(String name) {
        this.name = Objects.requireNonNull(name, "name");
    }

    public String getName() {
        return name;
    }

    public Customer registerCustomer(String customerId, String customerName) {
        Customer c = new Customer(customerId, customerName);
        customers.add(c);
        return c;
    }

    public CheckingAccount openCheckingAccount(Customer owner, BigDecimal opening, BigDecimal overdraftLimit) {
        CheckingAccount a = new CheckingAccount(owner, opening, overdraftLimit);
        owner.addAccount(a);
        return a;
    }

    public SavingsAccount openSavingsAccount(
            Customer owner,
            BigDecimal opening,
            BigDecimal minimumBalance,
            BigDecimal annualAprPercent) {
        SavingsAccount a = new SavingsAccount(owner, opening, minimumBalance, annualAprPercent);
        owner.addAccount(a);
        return a;
    }

    /**
     * Transfer between any two accounts at this bank (polymorphic {@link Account} references).
     */
    public void transfer(Account from, Account to, BigDecimal amount) throws InsufficientFundsException {
        Objects.requireNonNull(from, "from");
        Objects.requireNonNull(to, "to");
        if (from == to) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        from.withdraw(amount);
        to.deposit(amount);
    }

    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public Optional<Customer> findCustomerById(String id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}

package banking;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Base account type. Subclasses define withdrawal rules (polymorphism).
 * Balance uses {@link BigDecimal} for safe money arithmetic (interview talking point).
 */
public abstract class Account {
    private final String id;
    private final String accountNumber;
    private final Customer owner;
    private BigDecimal balance;

    protected Account(Customer owner, BigDecimal openingBalance) {
        this.id = UUID.randomUUID().toString();
        this.accountNumber = generateAccountNumber();
        this.owner = Objects.requireNonNull(owner, "owner");
        if (openingBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Opening balance cannot be negative");
        }
        this.balance = openingBalance;
    }

    private static String generateAccountNumber() {
        long n = (long) (Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return Long.toString(n);
    }

    public String getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Customer getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public final void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance = balance.add(amount);
    }

    /**
     * Subclasses enforce account-specific rules (e.g. overdraft, minimum balance).
     */
    public abstract void withdraw(BigDecimal amount) throws InsufficientFundsException;

    protected void subtractFromBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public abstract String accountType();

    @Override
    public String toString() {
        return accountType() + " #" + accountNumber + " balance=" + balance;
    }
}

package banking;

import java.math.BigDecimal;

/**
 * Everyday account with optional overdraft (subclass specialization).
 */
public class CheckingAccount extends Account {
    private final BigDecimal overdraftLimit;

    public CheckingAccount(Customer owner, BigDecimal openingBalance, BigDecimal overdraftLimit) {
        super(owner, openingBalance);
        if (overdraftLimit.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        BigDecimal newBalance = getBalance().subtract(amount);
        BigDecimal floor = overdraftLimit.negate();
        if (newBalance.compareTo(floor) < 0) {
            throw new InsufficientFundsException(
                    "Would exceed overdraft limit of " + overdraftLimit + " for account " + getAccountNumber());
        }
        subtractFromBalance(amount);
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public String accountType() {
        return "Checking";
    }
}

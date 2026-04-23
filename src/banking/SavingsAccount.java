package banking;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Savings with a minimum balance requirement after withdrawal.
 */
public class SavingsAccount extends Account {
    private final BigDecimal minimumBalance;
    private final BigDecimal annualInterestRate;

    public SavingsAccount(
            Customer owner,
            BigDecimal openingBalance,
            BigDecimal minimumBalance,
            BigDecimal annualInterestRate) {
        super(owner, openingBalance);
        if (minimumBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Minimum balance cannot be negative");
        }
        if (annualInterestRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Interest rate cannot be negative");
        }
        this.minimumBalance = minimumBalance;
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public void withdraw(BigDecimal amount) throws InsufficientFundsException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        BigDecimal after = getBalance().subtract(amount);
        if (after.compareTo(minimumBalance) < 0) {
            throw new InsufficientFundsException(
                    "Withdrawal would leave balance below minimum " + minimumBalance
                            + " for account " + getAccountNumber());
        }
        subtractFromBalance(amount);
    }

    /**
     * Simple monthly accrual for demo purposes (not production banking logic).
     */
    public void applyMonthlyInterest() {
        BigDecimal monthlyRate = annualInterestRate
                .divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        BigDecimal interest = getBalance().multiply(monthlyRate).setScale(2, RoundingMode.HALF_UP);
        if (interest.compareTo(BigDecimal.ZERO) > 0) {
            deposit(interest);
        }
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    @Override
    public String accountType() {
        return "Savings";
    }
}

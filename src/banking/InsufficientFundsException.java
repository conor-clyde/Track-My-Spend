package banking;

/**
 * Domain-specific checked-style exception for invalid withdrawals.
 */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

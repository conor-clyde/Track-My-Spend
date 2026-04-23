import banking.Account;
import banking.Bank;
import banking.CheckingAccount;
import banking.Customer;
import banking.InsufficientFundsException;
import banking.SavingsAccount;
import java.math.BigDecimal;

/**
 * Console demo: inheritance, polymorphism, encapsulation, and domain exceptions.
 */
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("Portfolio Community Bank");

        Customer alice = bank.registerCustomer("C-1001", "Alice");
        CheckingAccount checking =
                bank.openCheckingAccount(alice, new BigDecimal("500.00"), new BigDecimal("100.00"));
        SavingsAccount savings =
                bank.openSavingsAccount(alice, new BigDecimal("2000.00"), new BigDecimal("100.00"), new BigDecimal("3.5"));

        System.out.println("=== " + bank.getName() + " ===");
        System.out.println(alice);
        for (Account a : alice.getAccounts()) {
            System.out.println("  " + a);
        }

        try {
            checking.withdraw(new BigDecimal("550.00"));
            System.out.println("After $550 withdraw from checking: " + checking.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println("Expected guard: " + e.getMessage());
        }

        try {
            savings.withdraw(new BigDecimal("1950.00"));
        } catch (InsufficientFundsException e) {
            System.out.println("Savings rule: " + e.getMessage());
        }

        try {
            bank.transfer(checking, savings, new BigDecimal("50.00"));
            System.out.println("Transferred $50 checking -> savings");
            System.out.println("Checking: " + checking.getBalance() + " | Savings: " + savings.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        savings.applyMonthlyInterest();
        System.out.println("After monthly interest on savings: " + savings.getBalance());

        polymorphismDemo(alice.getAccounts());
    }

    /**
     * Same {@link Account} reference type, different runtime behavior (polymorphism).
     */
    private static void polymorphismDemo(java.util.List<Account> accounts) {
        System.out.println("\n--- Polymorphism: withdraw rules per concrete type ---");
        for (Account a : accounts) {
            System.out.println(a.accountType() + " withdraw policy is implemented in " + a.getClass().getSimpleName());
        }
    }
}

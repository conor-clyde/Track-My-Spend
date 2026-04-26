
import models.Customer;
import values.Categories;
import models.Account;
import models.CheckingAccount;
import models.SavingsAccount;
import models.Transaction;
import java.time.LocalDateTime;
import util.Util;

/**
 * Console demo: inherihelptance, polymorphism, encapsulation, and domain
 * exceptions.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Running Banking System");

        Customer customer = new Customer("Conor Clyde");
        Account checking = new CheckingAccount("Checking Account", 1200.00);
        Account savings = new SavingsAccount("Savings Account", 4000.00);
        customer.addAccount(checking);
        customer.addAccount(savings);

        Transaction expense = new Transaction(
                40.00,
                LocalDateTime.now(),
                Categories.FOOD,
                Transaction.TransactionType.EXPENSE);
        Transaction income = new Transaction(
                1200.00,
                LocalDateTime.now(),
                Categories.SALARY,
                Transaction.TransactionType.INCOME);
        checking.addTransaction(expense);
        checking.addTransaction(income);

        for (Account account : customer.getAccounts()) {
          
            for (Transaction transaction : account.getTransctions()) {
                Util.print(transaction.toString());
            }
        }
     
        

    }

}
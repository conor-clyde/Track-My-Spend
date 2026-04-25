
import models.User;
import values.Categories;
import models.Account;
import models.ExpenseTransaction;
import models.IncomeTransaction;

import java.time.LocalDateTime;

/**
 * Console demo: inherihelptance, polymorphism, encapsulation, and domain exceptions.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("Running Banking System");

        User user = new User("1234567890", "John Doe");
        Account account = new Account("My Account", 4000.00);
        user.addAccount(account);
        System.out.println(user);
        System.out.println(account);

        ExpenseTransaction transaction = new ExpenseTransaction("1234567890", 1000.00, LocalDateTime.now(), Categories.FOOD);
        account.addTransaction(transaction);
        System.out.println(transaction);

        IncomeTransaction incomeTransaction = new IncomeTransaction("1234567890", 1000.00, LocalDateTime.now(), Categories.SALARY);
        account.addTransaction(incomeTransaction);
        System.out.println(incomeTransaction);

    }

}
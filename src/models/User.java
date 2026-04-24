package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Owns accounts; list is defensively copied (encapsulation).
 */
public class User {
    private final String id;
    private String name;
    private List<Account> accounts = new ArrayList<>();

    public User(String id, String name) {
        this.id = UUID.randomUUID().toString();
        this.name = Objects.requireNonNull(name, "name");
        this.accounts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
    
}

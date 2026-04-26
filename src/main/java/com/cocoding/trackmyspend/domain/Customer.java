package com.cocoding.trackmyspend.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.cocoding.trackmyspend.domain.accounts.Account;

/**
 * Owns accounts; list is defensively copied (encapsulation).
 */
public class Customer {
    private final String id;
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
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

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    @Override
    public String toString() {
        return "Customer: Name=" + name + ", accounts=" +
        accounts.toString() + "]";
    }
}

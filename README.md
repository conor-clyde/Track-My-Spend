# Banking System (Java)

A small **object-oriented** banking domain model used for learning core OOP ideas and for talking through design in **junior software interviews**. It is not a real bank or production-grade payments system—it is a clear, readable codebase you can extend (transactions history, persistence, REST API, tests, and so on).

## What this demonstrates

| Concept | Where it shows up |
|--------|-------------------|
| **Encapsulation** | Private fields, accessors, `Customer.getAccounts()` returns an unmodifiable view |
| **Inheritance** | `CheckingAccount` and `SavingsAccount` extend `Account` |
| **Polymorphism** | `Bank.transfer(Account from, Account to, ...)` and `withdraw` behavior per subclass |
| **Abstraction** | `Account` is abstract; shared `deposit` logic, subclass-specific `withdraw` |
| **Domain modeling** | `Bank`, `Customer`, accounts, `InsufficientFundsException` |

## Requirements

- Java 17+ recommended (works with recent JDKs that ship `javac` / `java`)

## Build and run

From the project root:

```bash
mkdir -p out
javac -d out src/banking/*.java src/Main.java
java -cp out Main
```

## Suggested next steps (portfolio growth)

- JUnit tests for withdrawal rules and transfers
- `Transaction` record or class and an append-only history
- Persistence (file, SQLite, or JDBC)
- A thin CLI menu or a minimal HTTP API to discuss layering in interviews

## Interview practice prompts

- Why `BigDecimal` instead of `double` for money?
- What would you change before exposing this as a service?
- How would you model idempotency for transfers?

## License

This repository is for learning and portfolio use; add a license file if you want to clarify reuse terms.

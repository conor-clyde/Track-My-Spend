# TrackMySpend

TrackMySpend is a simple Java finance tracker used to practice OOP fundamentals, while keeping a Spring Boot starter setup ready for web/API work.

## Current project state

- Maven-based Java project
- Core domain model in `domain` (`Customer`, `Account`, `Transaction`)
- Category modeling split into `IncomeCategory` and `ExpenseCategory`
- Spring web endpoint in `web/HomeController`
- Two entry points:
  - Console OOP runner: `Main`
  - Spring Boot app runner: `FinanceApplication`

## Run

From the project root:

### Console OOP mode

```bash
mvn -q compile exec:java -Dexec.mainClass=com.cocoding.trackmyspend.Main
```

### Spring Boot mode

```bash
mvn spring-boot:run
```

## Repo

[https://github.com/conor-clyde/Track-My-Spend](https://github.com/conor-clyde/Track-My-Spend)

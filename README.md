# Finance Tracker

This repository is split into two separate apps so console OOP work and Spring Boot work can evolve independently.

## Structure

- `oop-console-app/` - pure Java console application (no Spring)
- `springboot-app/` - Spring Boot application (only Maven module)

## Run

### Console OOP app

```bash
cd oop-console-app
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out Main
```

### Spring Boot app

```bash
cd springboot-app
mvn spring-boot:run
```

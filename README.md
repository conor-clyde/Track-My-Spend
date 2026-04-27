# Finance Tracker

This repository is split into two separate apps so console OOP work and Spring Boot work can evolve independently.

## Structure

- `oop-console-app/` - pure Java console application (no Spring)
- `springboot-app/` - Spring Boot application

## Run

### Console OOP app

```bash
mvn -q -pl oop-console-app compile exec:java -Dexec.mainClass=com.cocoding.trackmyspend.Main
```

### Spring Boot app

```bash
mvn -pl springboot-app spring-boot:run
```

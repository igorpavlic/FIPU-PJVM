# Server Evidencija

Projekt za kolegij - web aplikacija za evidenciju servera i održavanja.

## Tehnologije
- Java 17
- Spring Boot 3.2
- Thymeleaf
- MySQL
- Maven

## Pokretanje

1. Kreiraj bazu u MySQL:
```sql
CREATE DATABASE server_evidencija;
```

2. Postavi lozinku u `application.properties`:
```
spring.datasource.password=tvoja_lozinka
```

3. Pokreni:
```
mvn spring-boot:run
```

4. Otvori: http://localhost:8080

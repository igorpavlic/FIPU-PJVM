# Server Evidencija

Web aplikacija za evidenciju servera i održavanja, izrađena u sklopu kolegija Programiranje na Java virtualnom stroju (FIPU).

Aplikacija omogućuje praćenje servera i svih intervencija (održavanja) koje su na njima izvršene, uz pretragu, statistiku i grafički prikaz podataka.

## Tehnologije

- Java 17
- Spring Boot 3.2
- Thymeleaf
- MySQL
- Maven
- Chart.js (grafovi)

## Funkcionalnosti

- Pregled, dodavanje, uređivanje i brisanje servera (CRUD)
- Evidencija intervencija po serveru (veza jedan-na-više)
- Pretraga servera po nazivu, IP adresi ili operativnom sustavu
- Validacija unosa s porukama o greškama
- Statistika svih intervencija (po tipu i po mjesecima)
- Top 5 servera po broju intervencija
- Grafički prikaz intervencija (Chart.js)
- Export popisa servera u CSV

## Pokretanje

1. Kreiraj bazu u MySQL:

```sql
CREATE DATABASE server_evidencija;
```

2. Postavi korisničko ime i lozinku u `src/main/resources/application.properties`:

```properties
spring.datasource.username=root
spring.datasource.password=tvoja_lozinka
```

3. Pokreni aplikaciju:

```bash
mvn spring-boot:run
```

4. Otvori u browseru: <http://localhost:8080>

Tablice se kreiraju automatski pri prvom pokretanju.
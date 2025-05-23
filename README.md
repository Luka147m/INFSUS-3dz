# INFSUS-3dz pokretanje projekta

## 1. Postavljanje baze podataka
- Potreban PostgreSQL 13+, psql cli ili pgadmin
- Kreirati bazu podataka
- Uvoz .sql datoteke baze podataka (nalazi se unutar /baza direktorija projekta) pomoću pgadmina ili psql naredbe  
```psql -U your_username -h your_host -p your_port -d your_target_database -f dump.sql```

## 2. Postavljanje backend aplikacije
- Potreban JDK17 i Maven
- Potrebno urediti application.properties datoteku
```
spring.datasource.url=jdbc:postgresql://localhost:5432/imebaze
spring.datasource.username=username
spring.datasource.password=lozinka
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
- Pozicionirati se u root direktorij backend projekta i pokrenuti aplikaciju pomoću naredbe ```mvn spring-boot:run```
- ili pokrenuti aplikaciju unutar vlastitog editora
- Aplikacija će se pokrenuti na portu 8080

## 3. Postavljanje frontend aplikacije
- Potreban node.js i npm
- Pozicionirati se unutar root direktorija frontend aplikacije i pokrenuti naredbe ```npm install``` i ```npm start```
- Aplikacija će se pokrenuti na portu 3000

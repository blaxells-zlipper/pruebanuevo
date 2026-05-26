# Spring Boot : Application PetClinic

## Features

### 1. Unit Test  --> Tag v1.0.0

mvn test -Dspring.profiles.active=h2

## Run project

Use Maven Wrapper on Windows:

```powershell
cd C:\Users\Mauricio\Desktop\tecsupp\PETCLINIC10\petclinic_test
.\mvnw.cmd spring-boot:run
```

Default profile is `h2`.

## Generate MySQL schema + seed data (scripts in `data/`)

The project includes:

- `data/schema-mysql.sql`
- `data/data-mysql.sql`

Run both scripts with:

```powershell
cd C:\Users\Mauricio\Desktop\tecsupp\PETCLINIC10\petclinic_test
.\data\load-mysql.ps1 -User root -Password "YOUR_PASSWORD"
```

Optional parameters:

```powershell
.\data\load-mysql.ps1 -User root -Password "YOUR_PASSWORD" -DbHost localhost -Port 3306
```

After loading MySQL data, run app with mysql profile:

```powershell
set DB_PASSWORD=YOUR_PASSWORD
.\mvnw.cmd "-Dspring.profiles.active=mysql" spring-boot:run
```

## Run only `PetServiceTest`

In PowerShell, quote `-Dtest` value:

```powershell
.\mvnw.cmd "-Dtest=com.tecsup.petclinic.services.PetServiceTest" clean test
```

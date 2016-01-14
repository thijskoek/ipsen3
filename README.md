# ipsen3 

## Setup
Dropwizard heeft wat configuratie vooraf nodig. Omdat ik Intellij gebruik leg ik het aan de hand
daarvan uit. Dit zal niet heel anders zijn voor andere IDE's.

Allereerst zul je een nieuw project aan moeten maken waarbij je deze als 'Existing source' aangeeft.

Intellij is slim genoeg en detecteert dat dit een Maven project is en haalt alle dependencies 
automatisch op.

In de root folder van dit project staat `ipsen3.yml`. Dit is het configuratie bestand voor deze 
applicatie. Hierin staan onder andere de database verbinding en server gegevens voor dit project.
Deze zul je moeten aanpassen naar jouw login gegevens etc.

Vervolgens zul je Run/Debug configurations moeten aanmaken. Open het Run/Debug configurations 
paneel en klik op het plusje rechts bovenin. Kies daar voor Application. Geef je configuration een 
naam, ik koos voor Server en selecteer de main class, dit is `App.java`. Vervolgens is het 
belangrijk dat je de volgende program arguments meegeeft: `server ipsen3.yml`. `ipsen3.yml` is 
hier het configratie bestand.

Wanneer je het programma nu uitvoert heb je een server draaien op `127.0.0.1:8080`.

## Database
In het bestand `ipsen3.yml` staan de inloggegevens voor je database. Dit is de database uit het
vorige project, IPSEN2. Deze kun je aanpassen naar je eigen login gegevens en deze hoef je niet 
te committen.

Tijdelijke hack: Zet de `debiteur_id` column in `user` default op een `debiteur_id` uit jouw huidige database.

```sql
CREATE TABLE IF NOT EXISTS "user" (
  id SERIAL NOT NULL PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  debiteur_id INT NOT NULL REFERENCES debiteur (id)
);

CREATE TABLE IF NOT EXISTS  role (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS  user_role (
  id SERIAL NOT NULL PRIMARY KEY,
  user_id INT NOT NULL REFERENCES "user" (id),
  role_id INT NOT NULL REFERENCES role (id),
  created_at TIMESTAMP NOT NULL DEFAULT current_timestamp,
  updated_at TIMESTAMP NOT NULL DEFAULT current_timestamp
);

ALTER TABLE debiteur
    ADD user_id INT REFERENCES "user" (id);
```

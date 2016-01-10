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
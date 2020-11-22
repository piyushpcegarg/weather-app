DROP TABLE IF EXISTS cities;

CREATE TABLE cities (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80),
  country VARCHAR(80),
  latitude DOUBLE,
  longitude DOUBLE
);
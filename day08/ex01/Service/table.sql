DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
id SERIAL PRIMARY KEY,
email text not null
);
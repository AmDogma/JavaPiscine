DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA chat;

CREATE TABLE chat.users (
    id SERIAL PRIMARY KEY,
    login text NOT NULL,
    password text NOT NULL
);

CREATE TABLE chat.rooms (
  id SERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  owner SERIAL REFERENCES chat.users(id) NOT NULL
);

CREATE TABLE chat.messages (
    id SERIAL PRIMARY KEY,
    author SERIAL REFERENCES chat.users (id) NOT NULL,
    room SERIAL REFERENCES chat.rooms (id) NOT NULL,
    message TEXT,
    dateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

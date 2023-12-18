CREATE TABLE IF NOT EXISTS users (
                                      id SERIAL PRIMARY KEY,
                                      user_name VARCHAR(32) UNIQUE NOT NULL,
                                      password VARCHAR(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS game (
                                    id SERIAL PRIMARY KEY,
                                    title VARCHAR(32),
                                    name VARCHAR(32)
);
CREATE TABLE IF NOT EXISTS users (
                                      user_id SERIAL PRIMARY KEY,
                                      user_name VARCHAR(32) UNIQUE NOT NULL,
                                      password VARCHAR(32) NOT NULL,
                                      balance INTEGER,
                                      token VARCHAR,
                                      deposit_id VARCHAR
);

CREATE TABLE IF NOT EXISTS game (
                                    game_id SERIAL PRIMARY KEY,
                                    title VARCHAR(32),
                                    name VARCHAR(32),
                                    price INTEGER
);

CREATE TABLE deposit (
                         id SERIAL PRIMARY KEY,
                         user_id BIGINT,
                         amount INTEGER,
                         deposit_id VARCHAR(255),
                         FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE user_game (
                           user_id BIGINT NOT NULL,
                           game_id BIGINT NOT NULL,
                           PRIMARY KEY (user_id, game_id),
                           FOREIGN KEY (user_id) REFERENCES user_table_name (user_id),
                           FOREIGN KEY (game_id) REFERENCES game_table_name (game_id)
);
DROP TABLE IF EXISTS big_items CASCADE;
CREATE TABLE big_items (
                           id serial PRIMARY KEY,
                           value int not null,
                           version bigint not null
);

INSERT INTO big_items(value,version) VALUES (1, 1);
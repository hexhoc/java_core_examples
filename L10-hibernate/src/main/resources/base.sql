--EXERCISE 1
CREATE TABLE IF NOT EXISTS public.catalogs
(
    id    BIGINT       not null PRIMARY KEY,
    title varchar(200) NOT NULL
);

--EXERCISE 2
-- 1. Create table 'authors'. Columns - id, name
-- 2. Create table 'books'. Columns id, title, author_id
-- 3. Create table 'readers'. Columns id, name
-- 4. Create table 'books_readers'. Column book_id, reader_id

DROP TABLE IF EXISTS authors CASCADE;
CREATE TABLE IF NOT EXISTS public.authors
(
    id   serial       NOT NULL UNIQUE,
    name varchar(200) NOT NULL,
    CONSTRAINT pk_author_id PRIMARY KEY (id)
);

INSERT INTO authors(name)
VALUES ('AUTHOR 1');
INSERT INTO authors(name)
VALUES ('AUTHOR 2');



DROP TABLE IF EXISTS books CASCADE;
CREATE TABLE IF NOT EXISTS public.books
(
    id        serial       not null unique,
    title     varchar(250) not null,
    author_id bigint       not null,
    constraint books_pk PRIMARY KEY (id),
    constraint authors_fk foreign key (author_id) references authors (id) on delete CASCADE
);

INSERT INTO books(title, author_id)
VALUES ('BOOK 1', 1);
INSERT INTO books(title, author_id)
VALUES ('BOOK 2', 2);



DROP TABLE IF EXISTS readers CASCADE;
CREATE TABLE IF NOT EXISTS public.readers
(
    id   serial       not null unique,
    name varchar(250) not null,
    constraint readers_pk PRIMARY KEY (id)
);

INSERT INTO readers(name)
VALUES ('READER 1');
INSERT INTO readers(name)
VALUES ('READER 2');
INSERT INTO readers(name)
VALUES ('READER 3');
INSERT INTO readers(name)
VALUES ('READER 4');


DROP TABLE IF EXISTS books_readers CASCADE;
create table if not exists public.books_readers
(
    books_id   bigint not null,
    readers_id bigint not null,
    constraint book_fk foreign key (books_id) references books on delete CASCADE,
    constraint reader_fk foreign key (readers_id) references readers on delete CASCADE
);

INSERT INTO books_readers(books_id, readers_id)
VALUES (1, 1);
INSERT INTO books_readers(books_id, readers_id)
VALUES (1, 2);
INSERT INTO books_readers(books_id, readers_id)
VALUES (2, 1);
INSERT INTO books_readers(books_id, readers_id)
VALUES (2, 2);
INSERT INTO books_readers(books_id, readers_id)
VALUES (1, 3);
INSERT INTO books_readers(books_id, readers_id)
VALUES (1, 4);

DROP TABLE IF EXISTS big_items CASCADE;
CREATE TABLE big_items
(
    id      serial PRIMARY KEY,
    value   int    not null,
    version bigint not null
);

INSERT INTO big_items(value, version)
VALUES (1, 1);
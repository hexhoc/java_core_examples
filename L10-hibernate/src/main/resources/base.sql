--EXERCISE 1
CREATE TABLE IF NOT EXISTS public.catalogs
(
    id    BIGINT       not null PRIMARY KEY,
    title varchar(200) NOT NULL
);

--EXERCISE 2
-- 1. Create table "authors". Columns - id, name
-- 2. Create table "books". Columns id, title, author_id
-- 3. Create table "readers". Columns id, name
-- 4. Create table "books_readers". Column book_id, reader_id

CREATE TABLE IF NOT EXISTS public.authors
(
    id   serial       NOT NULL UNIQUE,
    name varchar(200) NOT NULL,
    CONSTRAINT pk_author_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.books
(
    id        serial       not null unique,
    title     varchar(250) not null,
    author_id bigint not null,
    constraint books_pk PRIMARY KEY (id),
    constraint authors_fk foreign key (author_id) references authors (id) on delete CASCADE
);


CREATE TABLE IF NOT EXISTS public.readers
(
    id   serial       not null unique,
    name varchar(250) not null,
    constraint readers_pk PRIMARY KEY (id)
);

create table if not exists public.books_readers (
    books_id bigint not null,
    readers_id bigint not null,
    constraint book_fk foreign key (books_id) references books on delete CASCADE,
    constraint reader_fk foreign key (readers_id) references readers on delete CASCADE
)

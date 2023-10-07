
create table users (
    id integer not null primary key,
    login varchar(50) not null unique,
    password varchar(255) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null
);

create table roles (
    role_name varchar(50) primary key
);

create table user_role (
    user_id integer not null,
    role_name varchar(50) not null,

    -- Define the composite primary key
    PRIMARY KEY (user_id, role_name),

    -- Define foreign key constraints
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id), -- Assuming 'users' table has an 'id' column
    CONSTRAINT fk_role_name FOREIGN KEY (role_name) REFERENCES roles(role_name) -- Assuming 'roles' table has a 'value' column
);
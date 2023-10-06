insert into roles(role_name)
values ('ADMIN'),
       ('USER');

insert into users(id, login, password, first_name, last_name)
values (1, 'anton', '1234', 'Антон', 'Иванов'),
       (2, 'ivan', '12345', 'Сергей', 'Петров');

insert into user_role(user_id, role_name)
values (1, 'ADMIN'),
       (2, 'USER');
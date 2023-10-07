insert into roles(role_name)
values ('ADMIN'),
       ('USER');

insert into users(id, login, password, first_name, last_name)
values (1, 'anton', '$2a$10$x1QfWTYK7U3Nez4eTXBAm.x1lCx0fCWtd3oeBpb/UtzFh8IVE64yO', 'Антон', 'Иванов'),
       (2, 'ivan', '$2a$10$x1QfWTYK7U3Nez4eTXBAm.x1lCx0fCWtd3oeBpb/UtzFh8IVE64yO', 'Сергей', 'Петров');

insert into user_role(user_id, role_name)
values (1, 'ADMIN'),
       (2, 'USER');
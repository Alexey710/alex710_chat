--create table posts (
--  id serial primary key,
--  name varchar(2000),
--  description text,
--  created timestamp without time zone not null default now()
--);
--
--insert into posts (name) values ('О чем этот форум?');
--insert into posts (name) values ('Правила форума.');

create table authorities (
  id serial primary key,
  authority VARCHAR(50) not null unique
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

CREATE TABLE users (
        id serial primary key,
        username VARCHAR(50) NOT NULL unique,
        password VARCHAR(100) NOT NULL,
        enabled boolean default true,
        authority_id int not null references authorities(id)
        );
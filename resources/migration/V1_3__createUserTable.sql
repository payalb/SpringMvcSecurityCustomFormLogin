drop table if exists users;
drop table if exists roles;
create table users(
username varchar(30) primary key,
password varchar(100)
)

;
create table authorities(
id serial primary key ,
authority varchar(20),
username varchar(30) references users(username)
);

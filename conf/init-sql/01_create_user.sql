create table fundy_user
(
    role     int          not null,
    id       bigint auto_increment
        primary key,
    email    varchar(255) null,
    nickname varchar(255) not null unique,
    password varchar(255) not null unique
);
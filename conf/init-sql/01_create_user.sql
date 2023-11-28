create table fundy_user
(
    role     int          not null,
    id       bigint auto_increment
        primary key,
    email    varchar(255) null,
    nickname varchar(255) not null unique,
    profile varchar(255) not null default "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    password varchar(255) not null
);
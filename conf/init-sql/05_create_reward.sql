-- auto-generated definition
create table reward
(
    id bigint auto_increment primary key,
    title varchar(30) not null,
    item varchar(30),
    image varchar(255),
    minimumPrice bigint not null,
    project_id bigint       not null,
    foreign key (project_id) references project (id) on delete cascade
);

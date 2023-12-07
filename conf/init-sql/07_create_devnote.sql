create table devnote
(
    id bigint auto_increment primary key,
    project_id bigint not null,
    title varchar(255) not null,
    content varchar(255) not null,
    thumbnail varchar(255) not null,
    created_at datetime not null default current_timestamp,
    foreign key (project_id) references project(id)
)
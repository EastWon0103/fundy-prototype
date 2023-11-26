-- auto-generated definition
create table genres
(
    project_id bigint       not null,
    genres     varchar(255) null,
    constraint FKakcgoirh3mxngvtxjjv9qmlq2
        foreign key (project_id) references project (id)
);

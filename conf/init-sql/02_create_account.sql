create table account
(
    balance  int          null,
    id       bigint auto_increment
        primary key,
    owner_id bigint       null,
    number   varchar(255) null,
    constraint FKokwk8qd4ase9cd32dbvbtsag2
        foreign key (owner_id) references fundy_user (id)
);

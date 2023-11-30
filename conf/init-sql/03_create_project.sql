-- auto-generated definition
create table project
(
    devnote_upload_cycle int          not null,
    end_datetime         datetime(6)  not null,
    id                   bigint auto_increment
        primary key,
    owner_id             bigint       not null,
    start_datetime       datetime(6)  not null,
    content              varchar(255) not null,
    description          varchar(255) not null,
    devnote_upload_day   varchar(255) not null,
    title                varchar(255) not null,
    thumbnail                varchar(255) not null,
    deposit_account_id bigint not null,
    foreign key (deposit_account_id) references account (id),
    constraint FKmicjpnkjwo56ibo3d1430xl6f
        foreign key (owner_id) references fundy_user (id) on delete cascade
);


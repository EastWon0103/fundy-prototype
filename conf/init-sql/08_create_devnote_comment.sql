create table devnote_comment
(
    id bigint auto_increment primary key,
    writer_id bigint not null,
    devnote_id bigint not null,
    created_at datetime not null default current_timestamp,
    content varchar(255) not null,
    foreign key (writer_id) references fundy_user(id),
    foreign key (devnote_id) references devnote (id)
)
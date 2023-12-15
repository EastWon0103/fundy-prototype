create table funding_transaction
(
    id bigint auto_increment primary key,
    account_id bigint not null,
    reward_id bigint not null,
    transaction_datetime datetime not null default current_timestamp,
    amount int not null,
    is_refund int not null default 0,
    foreign key (account_id) references account (id),
    foreign key (reward_id) references reward (id)
);
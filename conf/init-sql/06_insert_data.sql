-- Creator User insert data
insert into fundy_user(id, email, password, nickname, role)
values (1, "test01@naver.com", "@Password1", "크리에이터1", 2),
       (2, "test02@naver.com", "@Password1", "크리에이터2", 2),
       (3, "test03@naver.com", "@Password1", "크리에이터3", 2);

-- User Accounts Setting
insert into account(id, number, balance, owner_id)
values (1,"24327-158-731", 100000, 1),
       (2,"25547-138-732", 100000, 2),
       (3,"25317-118-733", 100000, 3);
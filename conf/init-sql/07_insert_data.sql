-- Creator User insert data
insert into fundy_user(id, email, password, nickname, role)
values (1, "test01@naver.com", "@Password1", "크리에이터1", 2),
       (2, "test02@naver.com", "@Password1", "크리에이터2", 2),
       (3, "test03@naver.com", "@Password1", "크리에이터3", 2),
       (4, "test04@naver.com", "@Password1", "유저1", 2);


-- User Accounts Setting
insert into account(id, number, balance, owner_id)
values (1,"24327-158-731", 100000, 1),
       (2,"25547-138-732", 100000, 2),
       (3,"25317-118-733", 100000, 3),
       (4,"25327-118-733", 100000000, 4);

-- Project Setting
insert into project(id, title, content, description, thumbnail,
                    start_datetime, end_datetime,
                    devnote_upload_day, devnote_upload_cycle,
                    deposit_account_id, owner_id)
values (1, "프로젝트 1",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/Term+Project+Analysis+and+Design.pdf",
        "프로젝트 1은 프로젝트 1입니다", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
        "2023-10-11 09:00:00","2024-01-01 09:00:00",
        "FRIDAY", 1,
        1,1);

-- Genres Setting
insert into genres (project_id, genres)
values (1, "액션"),
       (1, "퍼즐");

-- Reward Setting
insert into reward (id, title, item, image, minimum_price, project_id)
values (1, "리워드1", "게임 A", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    50000, 1),
    (2, "리워드2", "게임 B", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    65000, 1),
    (3, "리워드3", "게임 C", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    100000, 1);

-- Funding Setting
insert into funding_transaction (id, account_id, reward_id,
                                 transaction_datetime, amount)
values (1,4,1,
        "2023-11-20 09:00:00",
        55000);
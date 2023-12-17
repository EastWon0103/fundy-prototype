-- Creator User insert data
insert into fundy_user(id, email, password, nickname, role)
values (1, "test01@naver.com", "@Password1", "크리에이터1", 2),
       (2, "test02@naver.com", "@Password1", "크리에이터2", 2),
       (3, "test03@naver.com", "@Password1", "크리에이터3", 2),
       (4, "test04@naver.com", "@Password1", "유저1", 2),
       (5,"test05@naver.com", "@Password1", "LAD", 2);


-- User Accounts Setting
insert into account(id, number, balance, owner_id)
values (1,"24327-158-731", 100000, 1),
       (2,"25547-138-732", 100000, 2),
       (3,"25317-118-733", 100000, 3),
       (4,"25327-118-733", 100000000, 4),
       (5,"25327-118-733", 100000000, 5);

-- Project Setting
insert into project(id, title, content, description, thumbnail,
                    start_datetime, end_datetime,
                    devnote_upload_day, devnote_upload_cycle,
                    deposit_account_id, owner_id, target_amount)
values (1, "용감한 토끼 탐험대의 모험, 플랫포머게임 LAPIN(라핀)",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/description.pdf",
        "''라핀''은 정교한 컨트롤로 위험한 지형지물을 돌파해 나가는 2D플랫포머 게임입니다.
공원 땅 밑에 살던 토끼들은 공원에 시작된 인간들의 공사로 인해 새로운 살 곳을 찾아 떠나야 합니다. 흰 토끼 ''리베''는 토끼 친구들을 이끄는 ''탐험대장''을 맡아, 지도를 따라 길을 탐험하며 새로운 동물 친구를 만나거나, 코인을 수집하며 낙원을 찾는 모험을 시작합니다. 토끼들은 새로운 살 곳을 찾을 수 있을까요? 그리고 토끼들은 왜 공원에 살게 되었을까요? 플랫포밍이 진행되며 낙원을 찾는 모험에 더해 토끼들에 대한 이야기가 펼쳐집니다.",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/thumbnail.png",
        "2023-10-11 09:00:00","2024-01-24 09:00:00",
        "FRIDAY", 1,
        5,5, 450000),
       (2, "프로젝트 2",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/Term+Project+Analysis+and+Design.pdf",
        "프로젝트 2은 프로젝트 2입니다", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
        "2023-10-11 09:00:00","2024-01-01 09:00:00",
        "FRIDAY", 1,
        1,1, 5000000);

-- Genres Setting
insert into genres (project_id, genres)
values (1, "액션"),
       (1, "RPG"),
       (2, "TPS"),
       (2, "퍼즐");

-- Reward Setting
insert into reward (id, title, item, image, minimum_price, project_id)
values (1, "라핀 A", "게임", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/thumbnail.png",
        50000, 1),
       (2, "라핀 B", "게임 + 10만원 상당 패키지", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/thumbnail.png",
        65000, 1),
       (3, "라핀 C", "게임 + 15만원 상당 패키지 + 키링", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/thumbnail.png",
        100000, 1),
    (4, "리워드1", "게임 A", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    50000, 2),
    (5, "리워드2", "게임 B", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    65000, 2),
    (6, "리워드3", "게임 C", "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png",
    100000, 2);

-- Funding Setting
insert into funding_transaction (id, account_id, reward_id,
                                 transaction_datetime, amount)
values (1,4,1,
        "2023-11-20 09:00:00",
        55000),
       (2,4,3,
        "2023-11-20 09:00:00",
        150000);

insert into devnote (id, project_id, title, content, thumbnail)
values (1, 1, "첫 번째 개발노트",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/devnote_1.pdf",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/thumbnail.png"),
       (2, 1, "두 번째 개발노트",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/devnote_2.pdf",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/thumbnail.png"),
       (3, 1, "세 번째 개발노트",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/devnote_3.pdf",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/thumbnail.png"),
        (4, 2, "첫 번째 개발노트",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/Term+Project+Analysis+and+Design.pdf",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png"),
       (5, 2, "두 번째 개발노트",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/Term+Project+Analysis+and+Design.pdf",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png"),
        (6, 2, "세 번째 개발노트",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/prototype/Term+Project+Analysis+and+Design.pdf",
        "https://fundy-bucket.s3.ap-northeast-2.amazonaws.com/default/profileImage.png");

insert into devnote_comment (id, writer_id, devnote_id, content)
values (1,1, 1, "재밌어 보이네요!!"),
       (2,2, 1, "화이팅 ㅎㅎ"),
       (3,3, 2, "이번에도 개발노트 내용이 좋네요!!");
## Fundy 프로토타입
### 목차
[1. Vision ](#1-Vision)   
[2. Scope ](#2-Scope)   
[3. Team ](#3-Team)   
[4. 실행 가이드 ](#4-실행-가이드)   
[5. 개발 가이드 ](#5-개발-가이드)
### 1. Vision
추후 업데이트 예정
### 2. Scope
추후 업데이트 예정
### 3. Team
추후 업데이트 예정
### 4. 실행 가이드
#### 1. 환경 세팅
1. Docker & Docker Compose 구성   
    > 편한 실행을 위해서 Docker Desktop을 추천합니다

   **Install Guide:**
[Cent OS](https://jsonobject.tistory.com/8)
/ [Ubuntu](https://haengsin.tistory.com/128)
/ [Mac](https://devzzi.tistory.com/71)
/ [Window](https://www.lainyzine.com/ko/article/a-complete-guide-to-how-to-install-docker-desktop-on-windows-10/)   

2. hosts 설정   
`hosts` 설정이 필요합니다. 만약 프로젝트를 더 이상 실행하지 않을 경우 추가한 내용을 Rollback 해주세요.
    ```
    # Linux & Mac hosts 위치: /etc/hosts 
    # Window hosts 위치: C:\Windows\System32\drivers\etc\hosts
    # 아래 내용 추가(기존 설정은 건들지 말기)
    127.0.0.1 fundy-game.com
    ```
#### 2. 파일 다운로드
`fundy-prototype` 프로젝트를 PC에 다운로드 혹은 Git Pull 받아주세요.
#### 3. 실행
1. 프로젝트 내부 폴더에서 터미널을 열어주세요.   
2. 다음 명령어를 입력해주세요
   ```
   docker-compose up -d --build 
   ## 혹은 
   docker compose up -d --build
   ```
#### 4. 어플리케이션 테스트
1. `8080`포트와 `3000`포트를 쓰는 어플리케이션이 있다면 종료해주세요.
2. `docker ps -a` 혹은 Docker Desktop의 `Dashboard`에서 컨테이너 실행 확인
    ```
    # builder 이외의 컨테이너가 실행 중인 상태이어야 함
    # PC 성능에 따라 빌드 시간에 차이가 있을 수 있습니다. (평균 5분 이하) 
    
    dongwon_kim@gimdong-won-ui-MacBookPro ~ % docker ps -a
    CONTAINER ID   IMAGE                         COMMAND                   CREATED          STATUS                      PORTS                                            NAMES
    72facbe33e5d   fundy-prototype-client        "/docker-entrypoint.…"   50 minutes ago   Up 48 minutes               80/tcp, 0.0.0.0:3000->3000/tcp                   client
    f62d1d387fc2   openjdk:17-ea-33-jdk-buster   "java -jar /build/li…"   50 minutes ago   Up 48 minutes               0.0.0.0:8080->8080/tcp                           api
    5f14a99fb9fc   mysql:8.0.33-oracle           "docker-entrypoint.s…"   50 minutes ago   Up 50 minutes               33060/tcp, 0.0.0.0:3310->3306/tcp                main-db
    86e998a9d1a1   nginx                         "/docker-entrypoint.…"   50 minutes ago   Up 50 minutes               0.0.0.0:80->80/tcp                               proxy_server
    95e13986823c   openjdk:17-ea-33-jdk-buster   "./gradlew clean bui…"   50 minutes ago   Exited (0) 48 minutes ago                                                    builder
    ```
3. [http://fundy-game.com](http://fundy-game.com)에 접속하여 제대로 실행이 되었는지 확인

### 5. 개발 가이드
[실행 가이드의 환경세팅](#1-환경-세팅)은 필수입니다.
#### Frontend
- `node` 환경 필요
- `client` 이외의 모든 컨테이너들을 실행. (API 연결을 위해)
- 모든 API의 URL은 `http://fundy-game.com/api` 로 시작합니다.
- `fundy-fe`의 폴더에서 `npm run start`로 실행할 수 있습니다. (localhost:3000)
- `fundy-game.com` 도메인에서 실행되는지 확인하고 싶을 경우, `client` 컨테이너 실행 후, [http://fundy-game.com](http://fundy-game.com)에 접속
- `fundy-game.com/api`은 백엔드에서 사용함으로 프론트엔드 url로 사용금지
- `메인 화면` url을 fundy-game.com과는 다르게 설정하고 싶으면 문의
#### Backend
- `Java 17` 이상의 환경 필요
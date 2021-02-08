# mybatis

mybatis 재상기 및 숙달 목적을 위한 프로젝트

### setup

- docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password --name ljk1112-mysql -v /Users/jklee/datadir_mysql:/var/lib/mysql mysql:8.0.17

혹은

`docker-compose.yaml` 위치에서 `docker-compose up -d` 를 실행 (-d 는 백그라운드)
```yaml
version: "3" # 파일 규격 버전
services: # 이 항목 밑에 실행하려는 컨테이너 들을 정의
  db: # 서비스 명
    image: mysql:8.0.17 # 사용할 이미지
    container_name: ljk1112-mysql # 컨테이너 이름 설정
    ports:
      - "3306:3306" # 접근 포트 설정 (컨테이너 외부:컨테이너 내부)
    environment: # -e 옵션
      MYSQL_ROOT_PASSWORD: "password"  # MYSQL 패스워드 설정 옵션
    command: # 명령어 실행, 한글 허용
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_unicode_ci
    volumes:
      - /Users/jklee/datadir:/var/lib/mysql # -v 옵션 (다렉토리 마운트 설정)
```

## Chapter1 : Mybatis 소개

---

### [step 0, 고전적인 방법](./src/main/java/com/example/jk/mybatis/step0/README.md)

### [step 1, SQL Mapper](./src/main/java/com/example/jk/mybatis/step1/README.md)

### [step 2, JDBC configuration](./src/main/java/com/example/jk/mybatis/step2/README.md)

### [step 3, Query Properties](./src/main/java/com/example/jk/mybatis/step3/README.md)

---

## Chapter2 : start Mybatis

### [Mapper, Config, Interface](./src/main/java/com/example/jk/mybatis/chapter02/README.md)


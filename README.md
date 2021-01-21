# mybatis

mybatis 재활치료(?) 목적을 위한 프로젝트

- docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password --name ljk1112-mysql -v /Users/jklee/datadir_mysql:/var/lib/mysql mysql:8.0.17
- docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password --name ljk1112-mysql -v /Users/jklee/datadir:/var/lib/mysql mysql:8.0.17

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
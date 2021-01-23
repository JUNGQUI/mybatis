### 고전적인 DB connection

- Connection 을 내부에서 호출, 정의
- Query 도 내부에서 선언
- 각 조건 또한 마찬가지로 내부에서 선언

단점

- 반복되는 부분 존재
    - JDBC driver set
    - connection set
    - query
- 변경에 취약
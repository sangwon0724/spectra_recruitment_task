# 스펙트라 채용 과제

## 시스템 아키텍처

### 개요

- 해당 애플리케이션은 Spring Boot와 H2 데이터베이스를 통해 개발되었습니다.
- 클라이언트
  - 브라우저
- Spring Boot 애플리케이션
  - 웹 서버, 비즈니스 로직, 데이터 접근 계층을 모두 포함
- H2 데이터베이스
  - 개발/테스트용 경량 RDBMS
  - 파일 기반이기 때문에 별도의 설치 없이 실행 가능

### 계층별 설명

- 클라이언트
  - 웹 브라우저
  - Thymeleaf를 활용해 구현
- Spring Boot 애플리케이션
  - Controller
    - 클라이언트 요청을 받아 처리
    - 유효성 검증 및 HTTP 요청/응답에 대한 처리
  - Service
    - 비즈니스 로직 수행
  - Repository
    - 데이터베이스와의 직접적인 통신 담당
    - JPA를 활용해 구현
  - DataSource
    - DB 연결 정보 관리
    - JPA에서 제공하는 DataSource 사용
- H2 데이터베이스
  - Java 기반의 경량 관계형 데이터베이스
  - 별도의 설치 과정을 생략하기 위해 파일 기반 사용이 가능한 H2 선택

## 통신 구조

- 클라이언트가 HTTP 요청을 Spring Boot 서버로 전송
- Spring Boot Controller가 요청을 받아 Service 계층에 전달
- Service 계층에서 비즈니스 로직 처리 후 Repository를 통해 DB에 접근
- Repository는 DataSource를 통해 H2 DB에 JPA를 통해 쿼리 실행
- DB에서 결과 반환 → Service → Controller → 클라이언트로 응답 반환

## 실행 및 접속 방법

1. 애플리케이션 실행
2. 다른 브라우저나 시크릿 탭을 활용하여 3개의 창을 띄우기
3. 브라우저에서 localhost:8080 접속
4. 원하는 계정에 맞는 로그인 방법 실행
    - 사용자
      - 인덱스 페이지에서 상담 유형 선택 후 상담 시작 버튼 클릭
    - 상담사
      - 인덱스 페이지 좌측 하단의 로그인 버튼을 통해 로그인 페이지에서 로그인 진행
      - 계정
        - user1 / test
        - user2 / test
        - user3 / test
    - 옵저버
        - 인덱스 페이지 좌측 하단의 로그인 버튼을 통해 로그인 페이지에서 로그인 진행
        - 계정
            - observer / test
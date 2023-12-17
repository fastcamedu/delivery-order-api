# 프로젝트 
- '주문 도메인 API' 프로젝트
- 주문을 전문적으로 다루는 도메인 API 서비스
- Role: **`delivery-order-api`**
- Port: **`40001`**

# 프로젝트 의존성
- MySQL 설치
  - 데이터베이스: **`food_delivery`**

# Tech Stack
- Spring Boot 3.1.5
- Spring Data JPA

# 시스템 구성과 흐름
```mermaid
flowchart LR
    delivery-store-app[상점 배달앱 \n port:20000] --> delivery-store-api[배달 고객 API \n port:20001] --> delivery-store[(상점 데이터베이스\ndelivery_store)]
    delivery-store-api[배달 고객 API \n port:20001] --> delivery-order-api[주문 도메인 API \n port:40001]
```

# 초기 접속 URL
- http://localhost:40001/

# 초기 계정
- ID: test@test.com
- PW: 1111
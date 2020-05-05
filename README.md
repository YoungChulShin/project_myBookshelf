문서 설명
- '나의 서재' 토이프로젝트의 설명 페이지입니다

`나의 서재` 프로젝트 소개
- 내가 읽은, 읽고 있는, 읽을 책의 리스트를 관리할 수 있는 페이지를 제공하는 프로젝트 입니다
- 간단하게 아래의 기능을 사용할 수 있습니다
   - 책 추가 및 수정
   - 독서 상태, 독서 기간, 간단한 메모 관리
   - 구글 로그인 기능을 제공합니다

사용 기술 정보
- 백엔드: 스프링부트
- 프론트엔드: 타임리프
- DB
   - MariaDB (상용 환경)
   - H2 (개발 환경)

프로젝트 실행 방법
- 사전 준비 사항
   - kakao developer의 API key
   - google oauth login 정보
- 프로젝트 실행 방법
   1. 소스를 Clone 받는다
   2. 아래 2개의 yml 파일을 추가한다
      - application-api.yml
         ~~~yml
         api-property:
          booksearch:
            api-url: https://dapi.kakao.com//v3/search/book
            api-key: API key를 넣어주세요
         ~~~
      - application-oauth.yml
         ~~~yml
         spring:
          security:
            oauth2:
              client:
                registration:
                  google:
                    client-id: client id를 넣어주세요
                    client-secret: client-secret을 넣어주세요 
                    scope: profile,email
         ~~~
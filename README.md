문서 설명
- '나의 서재' 토이프로젝트의 설명 페이지입니다

`나의 서재` 프로젝트 소개
- 내가 읽은, 읽고 있는, 읽을 책의 리스트를 관리할 수 있는 페이지를 제공하는 프로젝트 입니다
- 간단하게 아래의 기능을 사용할 수 있습니다
   - 책 추가 및 수정
   - 독서 상태, 독서 기간, 간단한 메모 관리
   - 알라딘 중고서점 재고 조회 기능을 지원합니다
   - 구글 로그인 기능을 제공합니다

사용 기술 정보
- 백엔드: 스프링부트
- 프론트엔드: 타임리프
- DB
   - MariaDB (상용 환경)
   - H2 (개발 환경)
- 서버
   - AWS EC2
- CI/CD
   - Travis CI
   - AWS S3
   - AWS Codedeploy

프로젝트 실행 방법
- 사전 준비 사항: 타 시스템의 API나 서비스를 사용하는 기능이 있어서 아래 항목이 필요합니다
   - kakao developer의 API key
      - 책 조회를 할 때 사용합니다
      - https://developers.kakao.com/ 사이트에서 발급받을 수 있습니다
   - aladin API key
      - 중고서점 재고 조회를 할 때 알라딘 API를 사용합니다
      - [https://blog.aladin.co.kr/openapi/category/29154404?communitytype=MyPaper](여기)를 참고해주세요
   - google oauth login 정보
      - 로그인을 위한 키 발급이 필요합니다
      - https://console.cloud.google.com/?hl=ko 에서 발급 받을 수 있습니다
- 프로젝트 실행 방법
   1. 소스를 Clone 받는다
   2. 아래 2개의 yml 파일을 추가한다
      - application-api.yml
         ~~~yml
         api-property:
           kakao-book-search:
             api-url: https://dapi.kakao.com//v3/search/book
             api-key: <카카오 API 키를 넣어주세요>
           aladin-book-search:
             api-url: http://www.aladin.co.kr/ttb/api/ItemOffStoreList.aspx
             api-key: <알라딘 API 키를 넣어주세요>
         ~~~
      - application-oauth.yml
         ~~~yml
         spring:
          security:
            oauth2:
              client:
                registration:
                  google:
                    client-id: <구글 client id>를 넣어주세요
                    client-secret: <구글 client-secret>을 넣어주세요 
                    scope: profile,email
         ~~~
   3. 빌드 및 실횅합니다
   5. 브라우저에서 `localhost:8080` 으로 접속해서 실행 테스트를 합니다
   
   
기타 설정 파일 설명
- `.travis.yml`
   - travis ci 설정 정보가 들어가 있습니다
- `appspec.yml`
   - codedeploy 설정 정보가 들어가 있습니다
- `scripts/deploy.sh`
   - ec2에 코드가 배포되었을 때 빌드 및 실행에 대한 스크립트 정보가 들어가 있습니다. 
# Introduction

### Oauth2-Authorization-Server
> Authorization Code 방식의 Oauth2.0 API Server 가상 구현

`SpringBoot` `lombok` `JPA` `H2`

<br>

- JWT 토큰 기반 구현
- 테스트를 위한 간단한 Resource Server API 포함
- 아쉽게도 프론트엔드 컴포넌트 X

<br>

### Update History

#### v1.0 [2022-01-31]
###### - 전체 Flow 반영된 초기버전 완성(예정)

<br>
<br>

## What is Oauth2 ?
🙋 작업 중


<br>
<br>

## What is JWT ?
🙋 작업 중


<br>
<br>

## Project

<br>

### Flows
<img src="https://blogfiles.pstatic.net/MjAyMTEyMTNfMjQ4/MDAxNjM5MzYyMTg0Mzkz.Ap39zMdWNqsRN2RcSdEGkEvKYTw1sdJ3hmaR6B_kZ1Eg.aiBJ9ZwfBJ7tjl-oBIIQyCE-_H9F6gNWSDNMAYqYk3Eg.PNG.good_ray/Authorization_Code_Grant_Type.png" width="60%" height="60%" title="oauth2" alt="oauth2"></img>
<br>

1. 사용자가 client에 접속합니다.

<br>

2. 리다이렉트 할 때 문자열 매개변수에 다음을 포함합니다.

* `response_type` 권한 부여 방식에 대한 설정방식으로 대표적으로 'code'와 'token'이 있음(이 프로젝트는 'code'로 고정)

* `redirect_uri`  요청을 승인 한 후 사용자를 다시 보낼 위치를 OAuth 서버에 알림

* `client_id`     개발자가 어플리케이션을 처음 등록 할 때 얻은 어플리케이션의 공개 식별자

* `state`         (Optional)어플리케이션에서 임의의 문자열을 생성하고 요청에 포함시킵니다. 그런 다음 인증 후에 동일한 값이 반환되는지 확인해야한다. CSRF 공격 을 막는 데 사용됨

* `scope`       ~~(Optional)어플리케이션이 요청한 사용 권한을 나타냄~~

3. 사용자가 로그인 정보를 전달합니다.(로그인 시도)

<br>

4. 로그인에 성공하면 2번에서 쿼리 스트링에 포함시켜 넘어온 client_id, redirect_uri등을 확인하여 일치하는지 확인합니다.

<br>

5. owner가 승인하면 client로 리다이렉트합니다.

<br>

6. client로 리다이렉트할 때 쿼리 매개변수에 다음을 포함합니다.

* `authorization code` OAuth 서버에서 만들어준 인증 코드

* `state`              (Optional) client에서 보낸 문자열 그대로 리턴

<br>

7. code를 통해 client가 authorization server에 직접 접근할 수 있게 됩니다. client가 POST요청을 보낼 때 쿼리 매개변수에 redirect_uri, grant_type, authorization을 포함합니다. client_id와 client_secret은 별도로 Base64 인코딩하여 생성하여 Authorization Basic 헤더에 넣어줍니다.

* `redirect_uri`       권한 서버가 요청에 대한 응답을 보낼 ur을 설정

* `grant_type`         Access Token 획득 요청 시 포함되는 값으로 권한 부여 방식에 대한 설정입니다.(grant_type은 'authorization_code'로 고정)

* `authorization code` OAuth 서버에서 만들어준 인증 코드

* `client_id`          개발자가 어플리케이션을 처음 등록 할 때 얻은 어플리케이션의 공개 식별자

* `client_secret`      이 값을 사용하면, access token을 얻으려는 요청이 어플리케이션에서만 이루어지며 인증 코드를 가로채는 잠재적인 공격자가 아닌 것이 보장된다.

<br>

8. code와 7번에서 받은 정보들을 확인하여 전부 일치하면 access token을 발급합니다. authorization server는 아래 내용을 포함하는 JSON파일로 응답합니다.

* `token_type`     발행된 Token의 타입(token_type은 'Bearer'로 고정)

* `expires_in`     토큰의 만료시간(초단위)

* `access_token`   Resource Server에 자원을 요청할 때 필요한 토큰

* `refresh_token`  access_token의 갱신용 토큰

<br>

9. client는 token을 통해 resource server에 직접 접근할 수 있게 됩니다.


<br>
<br>

### Database
🙋 작업 중


<br>
<br>

### End-points
🙋 작업 중


 

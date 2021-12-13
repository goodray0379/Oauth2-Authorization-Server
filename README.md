## Oauth2-Authorization-Server

> Authorization Code 방식의 Oauth2.0 API Server 가상 구현

`SpringBoot` `lombok` `JPA` `H2`

### Update History
* 21-12-30 (v1.0)
	* 전체 Flow 반영된 초기버전 완성(예정)

<br>

## What is Oauth2 ?
🙋 작업 중

<br>

## What is JWT ?
🙋 작업 중

<br>

## Project Flows

1. 사용자가 client에 접속합니다.

2. 리다이렉트 할 때 문자열 매개변수에 다음을 포함합니다.

`response_type` `client_id` `redirect_uri` `scope`

3. 사용자가 로그인 정보를 전달합니다(로그인 시도).

4. 로그인에 성공하면 2번에서 쿼리 스트링에 포함시켜 넘어온 클라이언트 아이디, 리다이렉트 uri등을 확인하여 일치하는지 확인합니다.

5, 6. owner가 승인하면 client로 리다이렉트할 때 쿼리 매개변수에 다음을 포함합니다.

`authorization code` `state`

7. code를 통해 client가 authorization server에 직접 접근할 수 있게 됩니다. client가 POST요청을 보낼 때 쿼리 매개변수에 다음을 포함합니다.

`client_id` `client_secret` `redirect_uri` `authorization code`

8. code와 7번에서 받은 정보들을 확인하여 전부 일치하면 access token을 발급합니다. authorization server는 아래 내용을 포함하는 JSON파일로 응답합니다.

`token_type` `expires_in` `access_token` `refresh_token`

9. client는 token을 통해 resource server에 직접 접근할 수 있게 됩니다.

<br>  

### Database
🙋 작업 중

<br>

### End-points
🙋 작업 중


 

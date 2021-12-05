package com.devmin.userservice.web;

import com.devmin.userservice.domain.user.Role;
import com.devmin.userservice.domain.user.UserRepository;
import com.devmin.userservice.service.user.UserService;
import com.devmin.userservice.web.dto.user.UserLoginRequestDto;
import com.devmin.userservice.web.dto.user.UserLoginResponseDto;
import com.devmin.userservice.web.dto.user.UserSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void User_회원가입한다() throws Exception {
        //given
        String username = "devmin";
        String password = "123123";
        Role role = Role.ADMIN;
        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();

        String url = "http://localhost:" + port + "/api/v1/users";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, userSaveRequestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
    }

    @Test
    public void User_로그인한다() throws Exception {
        //given
        Pattern pattern = Pattern.compile("(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$)");
        String username = "devmin";
        String password = "123123";

        userService.save(UserSaveRequestDto.builder()
                .username(username)
                .password(password)
                .build()
        );

        UserLoginRequestDto userLoginRequestDto = UserLoginRequestDto.builder()
                .username(username)
                .password(password)
                .build();

        String url = "http://localhost:" + port + "/api/v1/users/login";

        //when
        ResponseEntity<UserLoginResponseDto> responseEntity = restTemplate.postForEntity(url, userLoginRequestDto, UserLoginResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isGreaterThan(0L);

        assertThat(responseEntity.getBody().getUsername()).isEqualTo(username);
        assertThat(responseEntity.getBody().getTokens().get("accessToken")).matches(pattern);
        assertThat(responseEntity.getBody().getTokens().get("refreshToken")).matches(pattern);
    }
}

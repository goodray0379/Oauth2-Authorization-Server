package com.devmin.userservice.web;

import com.devmin.userservice.domain.user.User;
import com.devmin.userservice.domain.user.UserRepository;
import com.devmin.userservice.service.user.UserService;
import com.devmin.userservice.web.dto.UserLoginRequestDto;
import com.devmin.userservice.web.dto.UserLoginResponseDto;
import com.devmin.userservice.web.dto.UserSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void User_로그인한다() throws Exception {
        //given
        String username = "devmin";
        String password = "123123";

        UserSaveRequestDto userSaveRequestDto = UserSaveRequestDto.builder()
                .username(username)
                .password(password)
                .build();

        UserLoginRequestDto userLoginRequestDto = UserLoginRequestDto.builder()
                .username(username)
                .password(password)
                .build();

        String saveUrl = "http://localhost:" + port + "/api/v1/users";
        String loginUrl = "http://localhost:" + port + "/api/v1/users/login";

        //when
        restTemplate.postForLocation(saveUrl, userSaveRequestDto);
        ResponseEntity<UserLoginResponseDto> responseEntity = restTemplate.postForEntity(loginUrl, userLoginRequestDto, UserLoginResponseDto.class);

        //then
        assertThat(responseEntity.getBody().getUsername()).isEqualTo(username);
        Pattern pattern = Pattern.compile("(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$)");
        assertThat(responseEntity.getBody().getAccessToken()).as("패턴이 맞지 않습니다.").containsPattern(pattern);
    }
}

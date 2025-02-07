package com.devmin.oauth2.app.web.client;

import com.devmin.oauth2.app.domain.client.ClientRepository;
import com.devmin.oauth2.app.domain.user.User;
import com.devmin.oauth2.app.domain.user.UserRepository;
import com.devmin.oauth2.app.service.client.ClientService;
import com.devmin.oauth2.app.service.user.UserService;
import com.devmin.oauth2.app.web.dto.client.ClientResponseDto;
import com.devmin.oauth2.app.web.dto.client.ClientSaveRequestDto;
import com.devmin.oauth2.app.web.dto.client.ClientSaveResponseDto;
import com.devmin.oauth2.app.web.dto.user.UserLoginResponseDto;
import com.devmin.oauth2.app.web.dto.user.UserSaveRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientApiControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final static String USERNAME = "devmin";
    private final static String PASSWORD = "123123";
    private User user;
    private HttpHeaders headers;

    @Before
    public void setUp() throws Exception {
        Long id = userService.save(UserSaveRequestDto.builder()
                                    .username(USERNAME)
                                    .password(passwordEncoder.encode(PASSWORD))
                                    .build()
        );

        this.user = userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("해당 계정이 없습니다. 계정=" + id));

        // Header setting
        User entity = userRepository.findByUsername(USERNAME)
                .orElseThrow(()-> new IllegalArgumentException("해당 계정이 없습니다. 계정=" + USERNAME));
        UserLoginResponseDto userLoginResponseDto = userService.login(entity);
        this.headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + userLoginResponseDto.getAccessToken());
    }

    @After
    public void tearDown() throws Exception {
        clientRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void Client_등록한다() throws Exception {
        //given
        String clientName = "client-devmin";
        String companyName = "devmin";
        String webUrl = "https://client-devmin.com";
        String callbackUrl = "https://client-devmin.com/login?type=oauth";
        String description = "테스트 페이지";
        ClientSaveRequestDto clientSaveRequestDto = ClientSaveRequestDto.builder()
                                                    .clientName(clientName)
                                                    .companyName(companyName)
                                                    .webUrl(webUrl)
                                                    .callbackUrl(callbackUrl)
                                                    .description(description)
                                                    .build();

        String url = "http://localhost:" + port + "/api/v1/clients";
        HttpEntity<ClientSaveRequestDto> httpEntity =
                new HttpEntity<>(clientSaveRequestDto, this.headers);

        //when
        ResponseEntity<ClientSaveResponseDto> responseEntity =
                restTemplate.postForEntity(url, httpEntity, ClientSaveResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isGreaterThan(0L);

        System.out.println(">>>>>>> clientId : " + responseEntity.getBody().getClientId() + ",\n"
                            + ">>>>>>> clientSecret : " + responseEntity.getBody().getClientSecret());

        ClientResponseDto clientResponseDto =
                clientService.findById(responseEntity.getBody().getId());

        assertThat(clientResponseDto.getClientName()).isEqualTo(clientName);
        assertThat(clientResponseDto.getCompanyName()).isEqualTo(companyName);
        assertThat(clientResponseDto.getWebUrl()).isEqualTo(webUrl);
        assertThat(clientResponseDto.getCallbackUrl()).isEqualTo(callbackUrl);
        assertThat(clientResponseDto.getDescription()).isEqualTo(description);
    }

    @Test
    public void Client_찾는다() throws Exception {
        //given
        String clientName = "client-devmin";
        String companyName = "devmin";
        String webUrl = "https://client-devmin.com";
        String callbackUrl = "https://client-devmin.com/login?type=oauth";
        String description = "테스트 페이지";

        ClientSaveResponseDto clientSaveResponseDto =
                clientService.save(
                        ClientSaveRequestDto.builder()
                                .clientName(clientName)
                                .companyName(companyName)
                                .webUrl(webUrl)
                                .callbackUrl(callbackUrl)
                                .description(description)
                                .build(), user
                );

        String url = "http://localhost:" + port + "/api/v1/clients/" + clientSaveResponseDto.getId();

        //when
        ResponseEntity<ClientResponseDto> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ClientResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isGreaterThan(0L);

        assertThat(responseEntity.getBody().getClientName()).isEqualTo(clientName);
        assertThat(responseEntity.getBody().getCompanyName()).isEqualTo(companyName);
        assertThat(responseEntity.getBody().getWebUrl()).isEqualTo(webUrl);
        assertThat(responseEntity.getBody().getCallbackUrl()).isEqualTo(callbackUrl);
        assertThat(responseEntity.getBody().getDescription()).isEqualTo(description);
    }

    @Test
    public void Client_리스트_조회한다() throws Exception {
        //given
        int count = 3;
        final String DEVMIN = "devmin";

        for(int i=0; i<count; i++){
            String value = DEVMIN + i;
            clientService.save(ClientSaveRequestDto.builder()
                            .clientName(value)
                            .companyName(value)
                            .webUrl(value)
                            .callbackUrl(value)
                            .description(value)
                            .build(), user
            );
        }

        String url = "http://localhost:" + port + "/api/v1/clients/my-clients";

        //when
        ResponseEntity<ClientResponseDto[]> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), ClientResponseDto[].class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(count).isEqualTo(responseEntity.getBody().length);
        for(int i=0; i<count; i++){
            ClientResponseDto clientResponseDto = responseEntity.getBody()[i];
            String value = DEVMIN + i;

            System.out.println(">>>>>>> clientName : " + clientResponseDto.getClientName());

            assertThat(clientResponseDto.getClientName()).isEqualTo(value);
            assertThat(clientResponseDto.getCompanyName()).isEqualTo(value);
            assertThat(clientResponseDto.getWebUrl()).isEqualTo(value);
            assertThat(clientResponseDto.getCallbackUrl()).isEqualTo(value);
            assertThat(clientResponseDto.getDescription()).isEqualTo(value);
        }
    }
}

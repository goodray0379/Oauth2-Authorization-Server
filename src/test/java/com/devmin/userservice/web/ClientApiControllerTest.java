package com.devmin.userservice.web;

import com.devmin.userservice.domain.client.ClientRepository;
import com.devmin.userservice.service.user.ClientService;
import com.devmin.userservice.web.dto.client.ClientResponseDto;
import com.devmin.userservice.web.dto.client.ClientSaveRequestDto;
import com.devmin.userservice.web.dto.client.ClientSaveResponseDto;
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

    @After
    public void tearDown() throws Exception {
        clientRepository.deleteAll();
    }

    @Test
    public void Client_등록한다() throws Exception {
        //given
        String clientName = "client-devmin";
        String companyName = "devmin";
        String webUrl = "http://client-devmin:8080/";
        String callbackUrl = "http://client-devmin:8080/login?type=oauth";
        String description = "테스트 페이지";
        ClientSaveRequestDto clientSaveRequestDto = ClientSaveRequestDto.builder()
                .clientName(clientName)
                .companyName(companyName)
                .webUrl(webUrl)
                .callbackUrl(callbackUrl)
                .description(description)
                .build();

        String url = "http://localhost:" + port + "/api/v1/clients";

        //when
        ResponseEntity<ClientSaveResponseDto> responseEntity = restTemplate.postForEntity(url, clientSaveRequestDto, ClientSaveResponseDto.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isGreaterThan(0L);

        System.out.println(">>>>>>> clientId : " + responseEntity.getBody().getClientId() + ",\n"
                            + ">>>>>>> clientSecret : " + responseEntity.getBody().getClientSecret());

        ClientResponseDto clientResponseDto = clientService.findById(responseEntity.getBody().getId());

        assertThat(clientResponseDto.getClientName()).isEqualTo(clientName);
        assertThat(clientResponseDto.getCompanyName()).isEqualTo(companyName);
        assertThat(clientResponseDto.getWebUrl()).isEqualTo(webUrl);
        assertThat(clientResponseDto.getCallbackUrl()).isEqualTo(callbackUrl);
        assertThat(clientResponseDto.getDescription()).isEqualTo(description);
    }
}

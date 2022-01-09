package com.devmin.oauth2.app.service.client;

import com.devmin.oauth2.app.domain.client.Client;
import com.devmin.oauth2.app.domain.client.ClientRepository;
import com.devmin.oauth2.app.web.dto.client.ClientResponseDto;
import com.devmin.oauth2.app.web.dto.client.ClientSaveRequestDto;
import com.devmin.oauth2.app.web.dto.client.ClientSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ClientService{
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientResponseDto findById(Long id){
        Client client =  clientRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 클라이언트가 없습니다. id=" + id));
        return new ClientResponseDto(client);
    }

    @Transactional
    public ClientSaveResponseDto save(ClientSaveRequestDto clientSaveRequestDto){
        //Client ID와 Secret 정보 생성
        Map<String, String> clientInfos = new HashMap<>();
        clientInfos.put("clientId", createClientId());
        clientInfos.put("clientSecret", createClientSecret());
        Client entity= clientSaveRequestDto.toEntity(clientInfos);

        return new ClientSaveResponseDto(clientRepository.save( entity ));
    }

    public String createClientId(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public String createClientSecret(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}

package com.devmin.userservice.web.dto.client;
import com.devmin.userservice.domain.client.Client;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClientSaveResponseDto {

    private Long id;
    private String clientId;
    private String clientSecret;

    public ClientSaveResponseDto(Client entity) {
        this.id = entity.getId();
        this.clientId = entity.getClientId();
        this.clientSecret = entity.getClientSecret();
    }
}

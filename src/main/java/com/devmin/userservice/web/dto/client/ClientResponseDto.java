package com.devmin.userservice.web.dto.client;

import com.devmin.userservice.domain.client.Client;
import com.devmin.userservice.domain.user.Role;
import com.devmin.userservice.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClientResponseDto {
    private Long id;
    private String clientId;
    private String clientName;
    private String companyName;
    private String webUrl;
    private String callbackUrl;
    private String description;

    public ClientResponseDto(Client entity) {
        this.id = entity.getId();
        this.clientId = entity.getClientId();
        this.clientName = entity.getClientName();
        this.companyName = entity.getCompanyName();
        this.webUrl = entity.getWebUrl();
        this.callbackUrl = entity.getCallbackUrl();
        this.description = entity.getDescription();
    }
}

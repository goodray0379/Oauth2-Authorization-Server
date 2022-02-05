package com.devmin.oauth2.app.web.dto.client;

import com.devmin.oauth2.app.domain.client.Client;
import com.devmin.oauth2.app.domain.user.User;
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
    private User user;

    public ClientResponseDto(Client entity) {
        this.id = entity.getId();
        this.clientId = entity.getClientId();
        this.clientName = entity.getClientName();
        this.companyName = entity.getCompanyName();
        this.webUrl = entity.getWebUrl();
        this.callbackUrl = entity.getCallbackUrl();
        this.description = entity.getDescription();
        this.user = entity.getUser();
    }
}

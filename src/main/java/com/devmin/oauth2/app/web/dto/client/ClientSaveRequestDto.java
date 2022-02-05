package com.devmin.oauth2.app.web.dto.client;

import com.devmin.oauth2.app.domain.client.Client;
import com.devmin.oauth2.app.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ClientSaveRequestDto {

    private String clientName;
    private String companyName;
    private String webUrl;
    private String callbackUrl;
    private String description;
    private String user_id;

    @Builder
    public ClientSaveRequestDto(String clientName, String companyName, String webUrl, String callbackUrl, String description) {
        this.clientName = clientName;
        this.companyName = companyName;
        this.webUrl = webUrl;
        this.callbackUrl = callbackUrl;
        this.description = description;
    }

    public Client toEntity(Map<String, String> clientInfos) {
        return Client.builder()
                .clientName(clientName)
                .companyName(companyName)
                .webUrl(webUrl)
                .callbackUrl(callbackUrl)
                .description(description)
                .clientId(clientInfos.get("clientId"))
                .clientSecret(clientInfos.get("clientSecret"))
                .build();
    }

    public Client toEntity(Map<String, String> clientInfos, User user) {
        return Client.builder()
                .clientName(clientName)
                .companyName(companyName)
                .webUrl(webUrl)
                .callbackUrl(callbackUrl)
                .description(description)
                .clientId(clientInfos.get("clientId"))
                .clientSecret(clientInfos.get("clientSecret"))
                .user(user)
                .build();
    }
}

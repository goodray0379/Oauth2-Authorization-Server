package com.devmin.userservice.web.dto.client;
import com.devmin.userservice.domain.client.Client;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class ClientSaveRequestDto {

    private String clientName;
    private String companyName;
    private String url;
    private String callbackUrl;
    private String description;

    @Builder
    public ClientSaveRequestDto(String clientName, String companyName, String url, String callbackUrl, String description) {
        this.clientName = clientName;
        this.companyName = companyName;
        this.url = url;
        this.callbackUrl = callbackUrl;
        this.description = description;
    }

    public Client toEntity(Map<String, String> clientInfos) {
        return Client.builder()
                .clientName(clientName)
                .companyName(companyName)
                .url(url)
                .callbackUrl(callbackUrl)
                .description(description)
                .clientId(clientInfos.get("clientId"))
                .clientSecret(clientInfos.get("clientSecret"))
                .build();
    }
}

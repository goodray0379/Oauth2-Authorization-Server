package com.devmin.oauth2.app.web.dto.client;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRequestDto {
    private String redirectUrl;
    private String grantType;
    private String authorizationCode;
    private String clientId;
    private String clientSecret;

    @Builder
    public TokenRequestDto(String redirectUrl, String grantType, String authorizationCode, String clientId, String clientSecret) {
        this.redirectUrl = redirectUrl;
        this.grantType = grantType;
        this.authorizationCode = authorizationCode;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }
}

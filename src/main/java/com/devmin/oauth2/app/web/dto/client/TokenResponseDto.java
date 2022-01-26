package com.devmin.oauth2.app.web.dto.client;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenResponseDto {
    private String tokenType;
    private String expiresIn;
    private String accessToken;
    private String refreshToken;

    public TokenResponseDto(String tokenType, String expiresIn, String accessToken, String refreshToken) {
        this.tokenType = tokenType;
        this.expiresIn = expiresIn;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

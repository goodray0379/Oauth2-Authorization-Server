package com.devmin.oauth2.app.web.dto.client.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ClientUserLoginResponseDto {
    private String authorizationCode;
    private String state;

    public ClientUserLoginResponseDto(String authorizationCode, String state) {
        this.authorizationCode = authorizationCode;
        this.state = state;
    }
}

package com.devmin.oauth2.app.web.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    private String username;
    private String password;
    private String responseType;
    private String redirectUrl;
    private String clientId;
    private String state;

    @Builder
    public UserLoginRequestDto(String username, String password, String responseType, String redirectUrl, String clientId, String state) {
        this.username = username;
        this.password = password;
        this.responseType = responseType;
        this.redirectUrl = redirectUrl;
        this.clientId = clientId;
        this.state = state;
    }
}

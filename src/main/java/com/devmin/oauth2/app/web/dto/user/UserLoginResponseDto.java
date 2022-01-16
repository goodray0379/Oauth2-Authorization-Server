package com.devmin.oauth2.app.web.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginResponseDto {
    private String authorizationCode;
    private String state;

    public UserLoginResponseDto(String authorizationCode, String state) {
        this.authorizationCode = authorizationCode;
        this.state = state;
    }
}

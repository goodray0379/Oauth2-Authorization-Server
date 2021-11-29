package com.devmin.userservice.web.dto;

import com.devmin.userservice.domain.user.Role;
import com.devmin.userservice.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserLoginResponseDto {
    private Long id;
    private String username;
    private Role role;
    private String accessToken;
    //private String refreshToken;

    public UserLoginResponseDto(User entity, String accessToken) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.role = entity.getRole();
        this.accessToken = accessToken;
    }
}

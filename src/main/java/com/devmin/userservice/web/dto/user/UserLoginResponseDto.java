package com.devmin.userservice.web.dto.user;

import com.devmin.userservice.domain.user.Role;
import com.devmin.userservice.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
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

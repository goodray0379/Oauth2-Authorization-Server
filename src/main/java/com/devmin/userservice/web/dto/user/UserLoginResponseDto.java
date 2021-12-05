package com.devmin.userservice.web.dto.user;

import com.devmin.userservice.domain.user.Role;
import com.devmin.userservice.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
public class UserLoginResponseDto {
    private Long id;
    private String username;
    private Role role;
    private Map<String, String> tokens;

    public UserLoginResponseDto(User entity, Map<String, String> tokens) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.role = entity.getRole();
        this.tokens = tokens;
    }
}

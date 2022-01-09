package com.devmin.oauth2.app.web.dto.user;

import com.devmin.oauth2.app.domain.user.Role;
import com.devmin.oauth2.app.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private Role role;

    public UserResponseDto(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.role = entity.getRole();
    }
}

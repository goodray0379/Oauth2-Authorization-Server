package com.devmin.userservice.web.dto;

import com.devmin.userservice.domain.user.Role;
import com.devmin.userservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSaveRequestDto {
    private String username;
    private String password;
    private Role role;

    @Builder
    public UserSaveRequestDto(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .role(role)
                .build();
    }

    public void changePassword(String encryptedPassword){
        this.password = encryptedPassword;
    }
}

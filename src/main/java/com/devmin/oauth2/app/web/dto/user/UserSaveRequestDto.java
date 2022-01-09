package com.devmin.oauth2.app.web.dto.user;

import com.devmin.oauth2.app.domain.user.Role;
import com.devmin.oauth2.app.domain.user.User;
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

    public void encryptPassword(String encryptedPassword){
        this.password = encryptedPassword;
    }
}

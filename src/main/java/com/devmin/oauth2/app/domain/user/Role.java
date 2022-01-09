package com.devmin.oauth2.app.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(1,"ROLE_USER"),
    ADMIN(2, "ROLE_ADMIN");

    private final int key;
    private final String value;
}
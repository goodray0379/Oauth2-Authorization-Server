package com.devmin.userservice.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@NoArgsConstructor
public class TokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final String key = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";

    public String generateToken() {
        Date now = new Date();

        return Jwts.builder()
                .setIssuer("devmin")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
                .claim("username", "devmin")
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}

package com.devmin.userservice.component;

import com.devmin.userservice.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret-key}")
    private String key;
    @Value("${jwt.issuer}")
    private String issuer;
    final static long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;    // 30분
    final static long REFRESH_TOKEN_EXPIRE_TIME = 14 * 24 * 1000 * 60 * 60;    // 14일

    public String createAccessToken(User user){
        //토큰 발급
        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public String createRefreshToken(User user){
        //토큰 발급
        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }
}

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

    final static long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    final static String KEY = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";


    public UserLoginResponseDto(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.role = entity.getRole();
        makeToken();
    }

    public void makeToken(){
        //토큰 발급
        Date now = new Date();

        accessToken =  Jwts.builder()
                .setIssuer("devmin")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
                .claim("username", "devmin")
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }
}

package com.devmin.userservice.component;

import com.devmin.userservice.domain.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    final static String ISSUER = "devmin";
    final static long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;    // 30분
    final static String KEY = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";

    public String createToken(User user){
        //토큰 발급
        return Jwts.builder()
                .setIssuer(ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }
}

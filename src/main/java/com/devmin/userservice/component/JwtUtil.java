package com.devmin.userservice.component;

import com.devmin.userservice.domain.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtUtil {

    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret-key}")
    private String key;
    @Value("${jwt.issuer}")
    private String issuer;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        key = Base64.getEncoder().encodeToString(key.getBytes());
    }

    public String createAccessToken(User user){
        //토큰 발급
        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + ExpireTime.ACCESS_TOKEN_EXPIRE_TIME.getExpireTime()))
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public String createRefreshToken(User user){
        //토큰 발급
        return Jwts.builder()
                .setIssuer(issuer)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ExpireTime.REFRESH_TOKEN_EXPIRE_TIME.getExpireTime()))
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "Bearer <Token>"
    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token!=null && token.startsWith("Bearer ")){
            return token.substring(7);
        }
        return null;
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum ExpireTime{
        ACCESS_TOKEN_EXPIRE_TIME(1000 * 60 * 30),    // 30분
        REFRESH_TOKEN_EXPIRE_TIME(14 * 24 * 1000 * 60 * 60);    // 14일

        private final int expireTime;
    }
}

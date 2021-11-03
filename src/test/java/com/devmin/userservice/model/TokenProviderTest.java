package com.devmin.userservice.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenProviderTest {
    @Test
    public void 토큰_생성하기() {
        //given
        final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
        final String key = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";
        Date now = new Date();

        //when
        String jwt =  Jwts.builder()
                .setIssuer("devmin")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
                .claim("username", "devmin")
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        //then
        System.out.println(">>>>>>> jwt=" + jwt);
        Pattern pattern = Pattern.compile("(^[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*\\.[A-Za-z0-9-_]*$)");
        assertThat(jwt).as("패턴이 맞지 않습니다.").containsPattern(pattern);
    }
}

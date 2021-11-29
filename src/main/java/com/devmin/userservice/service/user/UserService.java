package com.devmin.userservice.service.user;

import com.devmin.userservice.domain.user.User;
import com.devmin.userservice.domain.user.UserRepository;
import com.devmin.userservice.web.dto.UserLoginRequestDto;
import com.devmin.userservice.web.dto.UserLoginResponseDto;
import com.devmin.userservice.web.dto.UserSaveRequestDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    final static long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    final static String KEY = "c3ByaW5nLWJvb3Qtc2VjdXJpdHktand0LXR1dG9yaWFsLWppd29vbi1zcHJpbmctYm9vdC1zZWN1cml0eS1qd3QtdHV0b3JpYWwK";

    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto){
        String username = userLoginRequestDto.getUsername();
        String password = userLoginRequestDto.getPassword();

        //계정 확인
        User entity = userRepository.findByUsername(username)
                .orElseThrow(()-> new IllegalArgumentException("해당 ID가 없습니다. ID=" + username));
        if(!passwordEncoder.matches(password, entity.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");

        //토큰 발급
        String accessToken = makeToken(entity);
        return new UserLoginResponseDto(entity, accessToken);
    }

    public void save(UserSaveRequestDto userSaveRequestDto){
        userSaveRequestDto.changePassword(passwordEncoder.encode(userSaveRequestDto.getPassword()));
        userRepository.save( userSaveRequestDto.toEntity() );
    }

    public String makeToken(User user){
        //토큰 발급
        Date now = new Date();

        return Jwts.builder()
                .setIssuer("devmin")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_EXPIRE_TIME))
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS512, KEY)
                .compact();
    }
}

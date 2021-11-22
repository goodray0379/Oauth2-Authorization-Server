package com.devmin.userservice.service.user;

import com.devmin.userservice.domain.user.User;
import com.devmin.userservice.domain.user.UserRepository;
import com.devmin.userservice.web.dto.UserLoginRequestDto;
import com.devmin.userservice.web.dto.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserLoginResponseDto findByUsername(UserLoginRequestDto userLoginRequestDto){
        String username = userLoginRequestDto.getUsername();
        String password = userLoginRequestDto.getPassword();
        User entity = userRepository.findByUsername(username)
                .orElseThrow(()-> new IllegalArgumentException("해당 ID가 없습니다. ID=" + username));
        System.out.println(password);
        System.out.println(entity.getPassword());
        if(passwordEncoder.matches(password, entity.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");

        return new UserLoginResponseDto(entity);
    }
}

package com.devmin.oauth2.app.service.user;

import com.devmin.oauth2.common.config.security.JwtTokenProvider;
import com.devmin.oauth2.app.domain.user.User;
import com.devmin.oauth2.app.domain.user.UserRepository;
import com.devmin.oauth2.app.web.dto.user.UserLoginResponseDto;
import com.devmin.oauth2.app.web.dto.user.UserResponseDto;
import com.devmin.oauth2.app.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public UserLoginResponseDto login(User entity){
        //토큰 발급
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", jwtTokenProvider.createAccessToken(entity));
        tokens.put("refreshToken", jwtTokenProvider.createRefreshToken(entity));

        entity.updateRefreshToken(tokens.get("refreshToken"));
        return new UserLoginResponseDto(entity, tokens);
    }

    public UserResponseDto findById(Long id){
        User user =  userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        return new UserResponseDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    public List<UserResponseDto> findAll(){
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        userRepository.findAll().forEach( user -> userResponseDtos.add( new UserResponseDto(user) ) );
        return userResponseDtos;
    }

    @Transactional
    public Long save(UserSaveRequestDto userSaveRequestDto){
        return userRepository.save( userSaveRequestDto.toEntity() ).getId();
    }
}

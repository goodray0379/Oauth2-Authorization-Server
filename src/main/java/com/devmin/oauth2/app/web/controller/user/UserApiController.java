package com.devmin.oauth2.app.web.controller.user;

import com.devmin.oauth2.app.domain.user.User;
import com.devmin.oauth2.app.domain.user.UserRepository;
import com.devmin.oauth2.app.service.user.UserService;
import com.devmin.oauth2.app.web.dto.user.UserLoginRequestDto;
import com.devmin.oauth2.app.web.dto.user.UserLoginResponseDto;
import com.devmin.oauth2.app.web.dto.user.UserResponseDto;
import com.devmin.oauth2.app.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserApiController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /*
     * 회원정보 저장
     * @return 유저아이디
     * @author devmin
     */
    @PostMapping
    public Long save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        userSaveRequestDto.encryptPassword(passwordEncoder.encode(userSaveRequestDto.getPassword()));
        return userService.save(userSaveRequestDto);
    }

    /*
     * 로그인
     * @return 토큰값
     * @author devmin
     */
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        //계정 확인
        String username = userLoginRequestDto.getUsername();
        String password = userLoginRequestDto.getPassword();
        User entity = userRepository.findByUsername(username)
                .orElseThrow(()-> new IllegalArgumentException("해당 ID가 없습니다. ID=" + username));
        if(!passwordEncoder.matches(password, entity.getPassword()))
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        return userService.login(entity);
    }

    /*
     * 회원정보 조회
     * @return 유저정보
     * @author devmin
     */
    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id){
        return userService.findById(id);
    }
}

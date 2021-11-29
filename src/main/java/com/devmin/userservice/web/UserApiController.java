package com.devmin.userservice.web;

import com.devmin.userservice.service.user.UserService;
import com.devmin.userservice.web.dto.UserLoginRequestDto;
import com.devmin.userservice.web.dto.UserLoginResponseDto;
import com.devmin.userservice.web.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/users/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }

    @PostMapping("/api/v1/users")
    public void save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        userService.save(userSaveRequestDto);
    }
}

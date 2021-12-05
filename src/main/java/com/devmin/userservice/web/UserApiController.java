package com.devmin.userservice.web;

import com.devmin.userservice.service.user.UserService;
import com.devmin.userservice.web.dto.user.UserLoginRequestDto;
import com.devmin.userservice.web.dto.user.UserLoginResponseDto;
import com.devmin.userservice.web.dto.user.UserResponseDto;
import com.devmin.userservice.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/users/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return userService.login(userLoginRequestDto);
    }

    @GetMapping("/users/{id}")
    public UserResponseDto findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping("/users")
    public Long save(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.save(userSaveRequestDto);
    }
}

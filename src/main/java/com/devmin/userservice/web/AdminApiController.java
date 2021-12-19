package com.devmin.userservice.web;

import com.devmin.userservice.service.user.UserService;
import com.devmin.userservice.web.dto.user.UserLoginRequestDto;
import com.devmin.userservice.web.dto.user.UserLoginResponseDto;
import com.devmin.userservice.web.dto.user.UserResponseDto;
import com.devmin.userservice.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/admins")
public class AdminApiController {

    private final UserService userService;


    @GetMapping("/users")
    public List<UserResponseDto> findAll(){
        return userService.findAll();
    }
}

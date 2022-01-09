package com.devmin.oauth2.app.web;

import com.devmin.oauth2.app.service.user.UserService;
import com.devmin.oauth2.app.web.dto.user.UserResponseDto;
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

package com.devmin.userservice.web;

import com.devmin.userservice.service.user.ClientService;
import com.devmin.userservice.service.user.UserService;
import com.devmin.userservice.web.dto.client.ClientResponseDto;
import com.devmin.userservice.web.dto.client.ClientSaveRequestDto;
import com.devmin.userservice.web.dto.client.ClientSaveResponseDto;
import com.devmin.userservice.web.dto.user.UserLoginRequestDto;
import com.devmin.userservice.web.dto.user.UserLoginResponseDto;
import com.devmin.userservice.web.dto.user.UserResponseDto;
import com.devmin.userservice.web.dto.user.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientApiController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ClientResponseDto findById(@PathVariable Long id){
        return clientService.findById(id);
    }

    @PostMapping("")
    public ClientSaveResponseDto save(@RequestBody ClientSaveRequestDto clientSaveRequestDto) {
        return clientService.save(clientSaveRequestDto);
    }
}

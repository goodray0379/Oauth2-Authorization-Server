package com.devmin.oauth2.app.web;

import com.devmin.oauth2.app.service.client.ClientService;
import com.devmin.oauth2.app.web.dto.client.ClientResponseDto;
import com.devmin.oauth2.app.web.dto.client.ClientSaveRequestDto;
import com.devmin.oauth2.app.web.dto.client.ClientSaveResponseDto;
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

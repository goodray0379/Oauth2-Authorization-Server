package com.devmin.oauth2.app.web.controller.client;

import com.devmin.oauth2.app.domain.user.User;
import com.devmin.oauth2.app.domain.user.UserRepository;
import com.devmin.oauth2.app.service.client.ClientService;
import com.devmin.oauth2.app.service.user.UserService;
import com.devmin.oauth2.app.web.dto.client.ClientResponseDto;
import com.devmin.oauth2.app.web.dto.client.ClientSaveRequestDto;
import com.devmin.oauth2.app.web.dto.client.ClientSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/clients")
public class ClientApiController {

    private final ClientService clientService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping()
    public ClientSaveResponseDto save(@RequestBody ClientSaveRequestDto clientSaveRequestDto
                                    , @AuthenticationPrincipal User user) {
        return clientService.save(clientSaveRequestDto, user);
    }

    @GetMapping("/{id}")
    public ClientResponseDto findById(@PathVariable Long id){
        return clientService.findById(id);
    }

    @GetMapping("/my-clients")
    public List<ClientResponseDto> findByUser(@AuthenticationPrincipal User user){
        return clientService.findByUsername(user);
    }

    //    @PostMapping("/users/login")
//    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
//        //계정 확인
//        String username = userLoginRequestDto.getUsername();
//        String password = userLoginRequestDto.getPassword();
//        User entity = userRepository.findByUsername(username)
//                .orElseThrow(()-> new IllegalArgumentException("해당 ID가 없습니다. ID=" + username));
//        if(!passwordEncoder.matches(password, entity.getPassword()))
//            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
//        //After To Do: 클라이언트 정보 확인 로직 추가해야함
//        return userService.createAuthorizationCode(entity, userLoginRequestDto);
//    }
}

package com.example.sofiyauserservice.controller;

import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @RequestMapping("/sign-up")
    public UserEntity signUp(@RequestBody UserCreatDto userCreatDto){
        return userService.save(userCreatDto);
    }


}

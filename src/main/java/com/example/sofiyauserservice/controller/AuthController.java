package com.example.sofiyauserservice.controller;

import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public UserEntity signUp(@RequestBody UserCreatDto userCreatDto){
        return userService.save(userCreatDto);
    }
    @PutMapping("/{code}/verify")
    public ResponseEntity<Boolean> verify(@PathVariable String code){
     return ResponseEntity.ok(true);
    }



}

package com.example.sofiyauserservice.controller;

import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PutMapping("/update")
    public ResponseEntity<UserEntity> update(
            @RequestParam UUID userId,
            @RequestBody UserCreatDto userCreatDto){
        return ResponseEntity.ok(userService.updateUser(userCreatDto,userId));
    }

}

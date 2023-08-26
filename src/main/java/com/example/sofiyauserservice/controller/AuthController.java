package com.example.sofiyauserservice.controller;

import com.example.sofiyauserservice.domain.dto.*;
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
    @PutMapping("/verify")
    public ResponseEntity<Boolean> verify(@RequestParam String code,@RequestParam String sendingCode){
     return ResponseEntity.ok(userService.verify(code,sendingCode));
    }
    @GetMapping("/new-code")
    public ResponseEntity<Boolean> getNewVerifyCode(@RequestParam String email){
        return ResponseEntity.ok(userService.getNewVerifyCode(email));
    }
    @GetMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(userService.signIn(loginDto));
    }
    @PostMapping("/seller/sign-up")
    public ResponseEntity<UserEntity> sellerSignUp(@RequestBody SellerDto sellerDto){
        return ResponseEntity.ok(userService.saveSeller(sellerDto));
    }
}

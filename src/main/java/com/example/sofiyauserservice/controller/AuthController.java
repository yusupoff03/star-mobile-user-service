package com.example.sofiyauserservice.controller;

import com.example.sofiyauserservice.domain.dto.JwtResponse;
import com.example.sofiyauserservice.domain.dto.LoginDto;
import com.example.sofiyauserservice.domain.dto.SellerDto;
import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.seller.SellerEntity;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.service.seller.SellerService;
import com.example.sofiyauserservice.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth")
public class AuthController {
    private final UserService userService;
    private final SellerService sellerService;

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
    public ResponseEntity<SellerEntity> sellerSignUp(@RequestBody SellerDto sellerDto){
        return ResponseEntity.ok(sellerService.save(sellerDto));
    }
}

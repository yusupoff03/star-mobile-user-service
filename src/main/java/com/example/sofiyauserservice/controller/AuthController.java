package com.example.sofiyauserservice.controller;

import com.example.sofiyauserservice.domain.dto.*;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.exception.RequestValidationException;
import com.example.sofiyauserservice.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserEntity> signUp(@Valid
                             @RequestBody UserCreatDto userCreatDto,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RequestValidationException(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(userService.save(userCreatDto));
    }

    @PutMapping("/verify")
    public ResponseEntity<Boolean> verify(@RequestParam UUID userId, @RequestParam String sendingCode) {
        return ResponseEntity.ok(userService.verify(sendingCode,userId));
    }

    @GetMapping("/new-code")
    public ResponseEntity<UserEntity> getNewVerifyCode(@RequestParam String email) {
        return ResponseEntity.ok(userService.getNewVerifyCode(email));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@Valid
                                                  @RequestBody LoginDto loginDto,
                                              BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new RequestValidationException(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(userService.signIn(loginDto));
    }

    @PostMapping("/seller/sign-up")
    public ResponseEntity<UserEntity> sellerSignUp(
            @Valid @RequestBody SellerDto sellerDto,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new RequestValidationException(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(userService.saveSeller(sellerDto));
    }
}

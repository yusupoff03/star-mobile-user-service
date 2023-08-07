package com.example.sofiyauserservice.service.user;

import com.example.sofiyauserservice.domain.dto.JwtResponse;
import com.example.sofiyauserservice.domain.dto.LoginDto;
import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;


public interface UserService {
    UserEntity save(UserCreatDto userCreatDto);
    Boolean verify(String code,String sendingCode);
    Boolean getNewVerifyCode(String email);
    JwtResponse signIn(LoginDto loginDto);
}

package com.example.sofiyauserservice.service.user;

import com.example.sofiyauserservice.domain.dto.JwtResponse;
import com.example.sofiyauserservice.domain.dto.LoginDto;
import com.example.sofiyauserservice.domain.dto.SellerDto;
import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import org.apache.catalina.User;

import java.util.UUID;


public interface UserService {
    UserEntity save(UserCreatDto userCreatDto);
    Boolean verify(String sendingCode,UUID userId);
    UserEntity getNewVerifyCode(String email);
    JwtResponse signIn(LoginDto loginDto);
    UserEntity saveSeller(SellerDto sellerDto);
    UserEntity updateUser(UserCreatDto userCreatDto, UUID userId);
}

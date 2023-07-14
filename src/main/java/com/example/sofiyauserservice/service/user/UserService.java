package com.example.sofiyauserservice.service.user;

import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;

public interface UserService {
    UserEntity save(UserCreatDto userCreatDto);
}

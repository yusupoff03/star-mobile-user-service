package com.example.sofiyauserservice.service.user;

import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.domain.entity.user.UserState;
import com.example.sofiyauserservice.repository.RoleRepository;
import com.example.sofiyauserservice.repository.UserRepository;
import com.example.sofiyauserservice.service.verificationcode.GenerateVerificationCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final GenerateVerificationCode generateVerificationCode;

    @Transactional
    public UserEntity save(UserCreatDto userCreatDto) {
        checkUserEmail(userCreatDto.getEmail());
        UserEntity userEntity = modelMapper.map(userCreatDto, UserEntity.class);
        userEntity.setVerificationCode(generateVerificationCode.generateVerificationCode(userEntity).getCode());// bu yerda aslida verificationcode packege dan foydalanish kere edi
        userEntity.setState(UserState.UNVERIFIED);
       return userRepository.save(userEntity);
    }

    private void checkUserEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("email already exists");
        }
    }


}

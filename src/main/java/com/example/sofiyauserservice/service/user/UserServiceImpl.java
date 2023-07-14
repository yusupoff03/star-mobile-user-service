package com.example.sofiyauserservice.service.user;

import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.domain.entity.user.UserState;
import com.example.sofiyauserservice.domain.entity.verification.VerificationCode;
import com.example.sofiyauserservice.repository.RoleRepository;
import com.example.sofiyauserservice.repository.UserRepository;
import com.example.sofiyauserservice.service.mailservice.MailService;
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
    private final MailService mailService;

    @Transactional
    public UserEntity save(UserCreatDto userCreatDto) {
        checkUserEmail(userCreatDto.getEmail());
        UserEntity userEntity = modelMapper.map(userCreatDto, UserEntity.class);
        userEntity.setState(UserState.UNVERIFIED);
        VerificationCode verificationCode = generateVerificationCode.generateVerificationCode(userEntity);
        userRepository.save(userEntity);;
        mailService.sendVerificationCode(userEntity.getEmail(),verificationCode.getSendingCode());
        return userEntity;
    }

    private void checkUserEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("email already exists");
        }
    }


}

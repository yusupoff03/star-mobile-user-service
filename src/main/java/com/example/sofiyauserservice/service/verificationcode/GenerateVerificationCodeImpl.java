package com.example.sofiyauserservice.service.verificationcode;

import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.domain.entity.verification.VerificationCode;
import com.example.sofiyauserservice.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class GenerateVerificationCodeImpl implements GenerateVerificationCode{
    private final VerificationCodeRepository verificationCodeRepository;

    public VerificationCode generateVerificationCode(UserEntity user) {
        int min = 100000;
        int max = 999999;
        Random random = new Random();
        int uniqueNumber = random.nextInt(max - min + 1) + min;
       String sendingCode=String.valueOf(uniqueNumber);
        return verificationCodeRepository.save(new VerificationCode(user,sendingCode,LocalDateTime.now().plusMinutes(10)));
    }

    @Override
    public VerificationCode getVerificationCode(String code) {
        return verificationCodeRepository.findVerificationCodeByCode(code);
    }
}

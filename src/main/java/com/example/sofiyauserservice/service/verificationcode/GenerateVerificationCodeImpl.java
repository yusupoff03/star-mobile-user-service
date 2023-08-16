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
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int CODE_LENGTH = 15;

    public VerificationCode generateVerificationCode(UserEntity user) {
        StringBuilder code1 = new StringBuilder(CODE_LENGTH);
        SecureRandom secureRandom = new SecureRandom();
        Random random=new Random(100000);
        String sendingCode=String.valueOf(random.nextInt(1000000));
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            code1.append(CHARACTERS.charAt(randomIndex));
        }
        return verificationCodeRepository.save(new VerificationCode(code1.toString(), user,sendingCode,LocalDateTime.now().plusMinutes(10)));
    }

    @Override
    public VerificationCode getVerificationCode(String code) {
        return verificationCodeRepository.findVerificationCodeByCode(code);
    }
}

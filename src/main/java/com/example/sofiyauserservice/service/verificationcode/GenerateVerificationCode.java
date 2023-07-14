package com.example.sofiyauserservice.service.verificationcode;

import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.domain.entity.verification.VerificationCode;
import com.example.sofiyauserservice.repository.VerificationCodeRepository;
import lombok.RequiredArgsConstructor;

import java.security.SecureRandom;

@RequiredArgsConstructor
public class GenerateVerificationCode {
    private final VerificationCodeRepository verificationCodeRepository;
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int CODE_LENGTH = 6;

    public VerificationCode generateVerificationCode(UserEntity user) {
        StringBuilder code1 = new StringBuilder(CODE_LENGTH);
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            code1.append(CHARACTERS.charAt(randomIndex));
        }
        return verificationCodeRepository.save(new VerificationCode(code1.toString(), user));
    }
}

package com.example.sofiyauserservice.repository;

import com.example.sofiyauserservice.domain.entity.verification.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, UUID> {
  VerificationCode findVerificationCodeByCode(String code);
}

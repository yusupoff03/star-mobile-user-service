package com.example.sofiyauserservice.domain.entity.verification;

import com.example.sofiyauserservice.domain.entity.BaseEntity;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity(name = "verification_code")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VerificationCode extends BaseEntity {
    private String code;
    @ManyToOne
    private UserEntity user;
}

package com.example.sofiyauserservice.domain.entity.seller;

import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@DiscriminatorColumn(name = "sellers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SellerEntity extends UserEntity {
    private String lastName;
    private String fatherName;
    private LocalDate birthDate;
    @Column(unique = true, nullable = false)
    private String passportNumber;
    @Column(unique = true,nullable =false)
    private String phoneNumber;
}

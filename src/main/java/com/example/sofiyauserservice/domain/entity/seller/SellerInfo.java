package com.example.sofiyauserservice.domain.entity.seller;

import com.example.sofiyauserservice.domain.entity.BaseEntity;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "seller_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellerInfo extends BaseEntity {
    private String lastName;
    private String fathersName;
    private LocalDate birthDate;
    @Column(unique = true)
    private String passportNumber;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String phoneNumber;
}

package com.example.sofiyauserservice.repository;

import com.example.sofiyauserservice.domain.entity.seller.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SellerRepository extends JpaRepository<SellerInfo,UUID> {
    SellerInfo findSellerInfoByPhoneNumberEquals(String phoneNumber);
    SellerInfo findSellerInfoByPassportNumberEquals(String passportNumber);
}

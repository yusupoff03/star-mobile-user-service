package com.example.sofiyauserservice.repository;

import com.example.sofiyauserservice.domain.entity.seller.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SellerRepository extends JpaRepository<SellerEntity, UUID> {
    
}

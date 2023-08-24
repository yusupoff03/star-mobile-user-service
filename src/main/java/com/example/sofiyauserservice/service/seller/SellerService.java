package com.example.sofiyauserservice.service.seller;

import com.example.sofiyauserservice.domain.dto.SellerDto;
import com.example.sofiyauserservice.domain.entity.seller.SellerEntity;

import java.util.UUID;

public interface SellerService {
  SellerEntity save(SellerDto sellerDto);
  Boolean update(SellerDto sellerDto);
  Boolean deleteById(UUID sellerId);
}

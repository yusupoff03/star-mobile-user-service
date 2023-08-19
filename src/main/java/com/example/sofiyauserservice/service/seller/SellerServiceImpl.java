package com.example.sofiyauserservice.service.seller;

import com.example.sofiyauserservice.domain.dto.SellerDto;
import com.example.sofiyauserservice.domain.entity.seller.SellerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{

    @Override
    public SellerEntity save(SellerDto sellerDto) {
        return null;
    }

    @Override
    public Boolean update(SellerDto sellerDto) {
        return null;
    }

    @Override
    public Boolean deleteById(UUID sellerId) {
        return null;
    }
}

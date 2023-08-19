package com.example.sofiyauserservice.controller;

import com.example.sofiyauserservice.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/seller")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;

}

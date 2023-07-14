package com.example.sofiyauserservice.service.mailservice;

public interface MailService{
     String sendVerificationCode(String email, String verificationCode);
}

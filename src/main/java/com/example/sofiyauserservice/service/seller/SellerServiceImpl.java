package com.example.sofiyauserservice.service.seller;

import com.example.sofiyauserservice.domain.dto.SellerDto;
import com.example.sofiyauserservice.domain.entity.seller.SellerEntity;
import com.example.sofiyauserservice.domain.entity.user.RoleEntity;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.domain.entity.user.UserState;
import com.example.sofiyauserservice.domain.entity.verification.VerificationCode;
import com.example.sofiyauserservice.exception.ConflictException;
import com.example.sofiyauserservice.repository.RoleRepository;
import com.example.sofiyauserservice.repository.SellerRepository;
import com.example.sofiyauserservice.service.mailservice.MailService;
import com.example.sofiyauserservice.service.verificationcode.GenerateVerificationCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{
   private final SellerRepository sellerRepository;
   private final ModelMapper modelMapper;
   private final GenerateVerificationCode generateVerificationCode;
   private final RoleRepository roleRepository;
   private final MailService mailService;
   private final PasswordEncoder passwordEncoder;
    @Override
    public SellerEntity save(SellerDto sellerDto) {
        if(!checkEmail(sellerDto.getEmail())){
           if(!checkPhoneNumber(sellerDto.getPhoneNumber())){
               if (!checkPassport(sellerDto.getPassportNumber())){
                   SellerEntity sellerEntity=modelMapper.map(sellerDto,SellerEntity.class);
                    RoleEntity role=roleRepository.findRoleEntityByNameEqualsIgnoreCase("Seller");
                    if(role==null){
                         role = roleRepository.save(new RoleEntity("Seller"));
                    }
                    sellerEntity.setRole(role);
                    sellerEntity.setState(UserState.UNVERIFIED);
                    SellerEntity seller=sellerRepository.save(sellerEntity);
                   UserEntity user=new UserEntity(sellerEntity.getName(),
                           sellerEntity.getEmail(),
                           sellerEntity.getPassword(),
                           sellerEntity.getRole(),
                           sellerEntity.getState());
                   user.setId(seller.getId());
                   VerificationCode verificationCode=generateVerificationCode.generateVerificationCode(user);
                   sellerEntity.setPassword(passwordEncoder.encode(sellerEntity.getPassword()));
                   mailService.sendVerificationCode(sellerEntity.getEmail(),verificationCode.getSendingCode());
                  return sellerRepository.save(sellerEntity);
               }
               throw new ConflictException("This passport already exists");
           }
           throw new ConflictException("This phone number already exists");
        }
        throw new ConflictException("This email already exists");
    }

    @Override
    public Boolean update(SellerDto sellerDto) {
        return null;
    }

    @Override
    public Boolean deleteById(UUID sellerId) {
        return null;
    }
    public Boolean checkEmail(String email){
        SellerEntity sellerEntityByEmailEquals = sellerRepository.findSellerEntityByEmailEquals(email);
        if(sellerEntityByEmailEquals==null){
            return false;
        }
        return true;
    }
    public Boolean checkPhoneNumber(String phoneNumber){
        SellerEntity sellerEntityByEmailEquals = sellerRepository.findSellerEntityByPhoneNumberEquals(phoneNumber);
        if(sellerEntityByEmailEquals==null){
            return false;
        }
        return true;
    }
    public Boolean checkPassport(String passport){
        SellerEntity sellerEntityByEmailEquals = sellerRepository.findSellerEntityByPassportNumberEquals(passport);
        if(sellerEntityByEmailEquals==null){
            return false;
        }
        return true;
    }
}

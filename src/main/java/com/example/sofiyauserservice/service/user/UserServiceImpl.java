package com.example.sofiyauserservice.service.user;

import com.example.sofiyauserservice.domain.dto.JwtResponse;
import com.example.sofiyauserservice.domain.dto.LoginDto;
import com.example.sofiyauserservice.domain.dto.SellerDto;
import com.example.sofiyauserservice.domain.dto.UserCreatDto;
import com.example.sofiyauserservice.domain.entity.seller.SellerInfo;
import com.example.sofiyauserservice.domain.entity.user.RoleEntity;
import com.example.sofiyauserservice.domain.entity.user.UserEntity;
import com.example.sofiyauserservice.domain.entity.user.UserState;
import com.example.sofiyauserservice.domain.entity.verification.VerificationCode;
import com.example.sofiyauserservice.exception.AuthenticationFailedException;
import com.example.sofiyauserservice.exception.ConflictException;
import com.example.sofiyauserservice.exception.DataNotFoundException;
import com.example.sofiyauserservice.repository.RoleRepository;
import com.example.sofiyauserservice.repository.SellerRepository;
import com.example.sofiyauserservice.repository.UserRepository;
import com.example.sofiyauserservice.service.JwtService;
import com.example.sofiyauserservice.service.mailservice.MailService;
import com.example.sofiyauserservice.service.verificationcode.GenerateVerificationCode;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final GenerateVerificationCode generateVerificationCode;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final SellerRepository sellerRepository;

    @Transactional
    public UserEntity save(UserCreatDto userCreatDto) {
        checkUserEmail(userCreatDto.getEmail());
        UserEntity userEntity = modelMapper.map(userCreatDto, UserEntity.class);
        userEntity.setState(UserState.UNVERIFIED);
        RoleEntity userRole = roleRepository.findRoleEntityByNameEqualsIgnoreCase("User");
        if (userRole != null) {
            VerificationCode verificationCode = generateVerificationCode.generateVerificationCode(userEntity);
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userEntity.setRole(userRole);
            userRepository.save(userEntity);
            mailService.sendVerificationCode(userEntity.getEmail(), verificationCode.getSendingCode());
        }
        if (userRole == null) {
            RoleEntity save = roleRepository.save(new RoleEntity("User"));
            userEntity.setRole(save);
            VerificationCode verificationCode = generateVerificationCode.generateVerificationCode(userEntity);
            userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userRepository.save(userEntity);
            mailService.sendVerificationCode(userEntity.getEmail(), verificationCode.getSendingCode());
        }
        return userEntity;
    }

    @Override
    public Boolean verify(String code, String sendingCode) {
        VerificationCode verificationCode = generateVerificationCode.getVerificationCode(code);
        if (verificationCode == null) {
            throw new DataNotFoundException("Verification Code did not match");
        }
        if (Objects.equals(sendingCode, verificationCode.getSendingCode())
                && LocalDateTime.now().isBefore(verificationCode.getExpiryDate())) {
            UserEntity user = verificationCode.getUser();
            user.setState(UserState.ACTIVE);
            userRepository.save(user);
            return true;
        }
        throw new AuthenticationFailedException("Code is incorrect or Code is ragged");
    }

    @Override
    public Boolean getNewVerifyCode(String email) {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new DataNotFoundException("User not found,Please sign up"));
        //  generateVerificationCode.deleteUserCode(userEntity);
        VerificationCode verificationCode = generateVerificationCode.generateVerificationCode(userEntity);
        mailService.sendVerificationCode(email, verificationCode.getSendingCode());
        return true;
    }

    @Override
    public JwtResponse signIn(LoginDto loginDto) {
        UserEntity user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            if (user.getState().equals(UserState.ACTIVE)) {
                return JwtResponse.builder().accessToken(jwtService.generateAccessToken(user)).build();
            } else {
                throw new AuthenticationFailedException("You are not verified user! Please verify your account and login");
            }
        }
        throw new DataNotFoundException("User not found");
    }

    @Override
    public UserEntity saveSeller(SellerDto sellerDto) {
        RoleEntity role = checkRole("Seller");
        SellerInfo sellerInfo1 = checkPassport(sellerDto.getPassportNumber());
        if(role==null){
            role = roleRepository.save(new RoleEntity("Seller"));
        }
        UserEntity user=
                new UserEntity(sellerDto.getName(),
                        sellerDto.getEmail(),
                        sellerDto.getPassword(),
                        role,
                        UserState.UNVERIFIED);
        UserEntity save = userRepository.save(user);
        if(sellerInfo1==null){
            SellerInfo sellerInfo=new SellerInfo
                    (sellerDto.getLastName(),
                            sellerDto.getFatherName(),
                            sellerDto.getBirthDate()
                            ,sellerDto.getPassportNumber()
                            ,save
                            ,sellerDto.getPhoneNumber());
           sellerRepository.save(sellerInfo);
        }else {
            throw new ConflictException("This Seller information already exists");
        }
      return save;
    }

    private void checkUserEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ConflictException("email already exists");
        }
    }
    public SellerInfo checkPassport(String passport){
        return sellerRepository.findSellerInfoByPassportNumberEquals(passport);
    }
    public RoleEntity checkRole(String name){
        return roleRepository.findRoleEntityByNameEqualsIgnoreCase(name);
    }


}

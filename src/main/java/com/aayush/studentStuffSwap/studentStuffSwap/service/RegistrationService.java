package com.aayush.studentStuffSwap.studentStuffSwap.service;

import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.TempUserInputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.VerificationInputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.helper.OtpGenerator;
import com.aayush.studentStuffSwap.studentStuffSwap.model.TempUser;
import com.aayush.studentStuffSwap.studentStuffSwap.model.User;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.TempUserRepository;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;

import static java.time.LocalDate.now;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TempUserRepository tempUserRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private VerificationService verificationService;

    public ResponseEntity<String> registerTempUser(TempUserInputDTO registrationDTO) {
        try {
            if (userAlreadyExists(registrationDTO.getEmail(), registrationDTO.getScholarNumber())) {
                return userAlreadyExistsResponse();
            }
            TempUser tempUser = createTempUserInputDTO(registrationDTO);
            saveTempUser(tempUser);
            sendVerificationEmail(tempUser.getEmail(), tempUser.getVerificationToken());
            return handleSuccessfulRegistration(tempUser.getEmail());
        } catch (Exception e) {
            return handleRegistrationError(e);
        }
    }

    private TempUser createTempUserInputDTO(TempUserInputDTO registrationDTO) {
        TempUser tempUser = new TempUser();
        tempUser.setEmail(registrationDTO.getEmail());
        tempUser.setPassword(hashPassword(registrationDTO.getPassword()));
        tempUser.setFirstName(registrationDTO.getFirstName());
        tempUser.setLastName(registrationDTO.getLastName());
        tempUser.setPhoneNumber(registrationDTO.getPhoneNumber());
        tempUser.setScholarNumber(registrationDTO.getScholarNumber());
        tempUser.setIdCardImageUrl(registrationDTO.getIdCardImageUrl());
        tempUser.setVerificationToken(OtpGenerator.generateOTP(6));
        tempUser.setVerificationStatus(false);
        tempUser.setRegistrationDate(now());
        return tempUser;
    }

    public ResponseEntity<String> registerUser(VerificationInputDTO verificationInputDTO) {
        TempUser tempUser = tempUserRepository.findByEmail(verificationInputDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        if(VerificationService.verifyEmail(tempUser, verificationInputDTO.getVerificationCode())) {
           return completeUserRegistration(tempUser);
        }
        return new ResponseEntity<>("otp wrong hai ji" , HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> completeUserRegistration(TempUser tempUser) {
        try {
            String response;
            User user = createUserFromTemporaryUser(tempUser);
            removeTemporaryUser(tempUser);
            response = generateSuccessResponse(user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return handleRegistrationError(e);
        }
    }

    private User createUserFromTemporaryUser(TempUser tempUser) {
        User user = new User();
        user.setEmail(tempUser.getEmail());
        user.setFirstName(tempUser.getFirstName());
        user.setLastName(tempUser.getLastName());
        user.setPassword(tempUser.getPassword());
        user.setScholarNumber(tempUser.getScholarNumber());
        user.setPhoneNumber(tempUser.getPhoneNumber());
        user.setIdCardImageUrl(tempUser.getIdCardImageUrl());
        user.setRegistrationDate(LocalDateTime.now().toLocalDate());
        user.setRoles(new HashSet<>());
        userRepo.save(user);
        return user;
    }

    private String generateSuccessResponse(User user) {
        return "Registration successful for " + user.getFirstName()
                + " " + user.getLastName() + " ,Login to buy/sell/rent";
    }

    private void removeTemporaryUser(TempUser tempUser) {
        tempUserRepository.delete(tempUser);
    }

    private void saveTempUser(TempUser tempUser) {
        tempUserRepository.save(tempUser);
    }

    private ResponseEntity<String> handleRegistrationError(Exception e) {
        String errorMessage = "Registration failed. An error occurred during temporary registration.";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private  ResponseEntity<String> handleSuccessfulRegistration(String email) {
        return new ResponseEntity<>("Please check your email " + email + " for a verification OTP to complete registration.", HttpStatus.OK);
    }

    private String hashPassword(String userPassword) {
        return new BCryptPasswordEncoder(8).encode(userPassword);
    }

    private boolean userAlreadyExists(String emailId, String scholarNumber) {
        Optional<User> userWithEmail = userRepo.findByEmail(emailId);
        Optional<User> userWithScholarNumber = userRepo.findByScholarNumber(scholarNumber);

        return userWithEmail.isPresent() || userWithScholarNumber.isPresent();
    }

    private ResponseEntity<String> userAlreadyExistsResponse() {
        return new ResponseEntity<>("User Already Exists", HttpStatus.BAD_REQUEST);
    }
    private void sendVerificationEmail(String email, String verificationToken) {
        emailService.sendVerificationEmail(email,verificationToken);
    }
}



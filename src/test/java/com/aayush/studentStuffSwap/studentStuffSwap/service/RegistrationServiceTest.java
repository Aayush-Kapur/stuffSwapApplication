package com.aayush.studentStuffSwap.studentStuffSwap.service;

import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.TempUserInputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.VerificationInputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.model.TempUser;
import com.aayush.studentStuffSwap.studentStuffSwap.model.User;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.TempUserRepository;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TempUserRepository tempUserRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private VerificationService verificationService;

    @InjectMocks
    private RegistrationService registrationService;

    @Test
    void registerTempUser_SuccessfulRegistration() {
        // Arrange
        TempUserInputDTO registrationDTO = new TempUserInputDTO();
        registrationDTO.setEmail("test@example.com");
        registrationDTO.setPassword("password");
        registrationDTO.setFirstName("John");
        registrationDTO.setLastName("Doe");
        registrationDTO.setPhoneNumber("1234567890");
        registrationDTO.setScholarNumber("S123456");
        registrationDTO.setIdCardImageUrl("path/to/id/card");

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(userRepository.findByScholarNumber(any())).thenReturn(Optional.empty());

        ResponseEntity<String> response = registrationService.registerTempUser(registrationDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Please check your email"));

        verify(emailService, times(1)).sendVerificationEmail(anyString(), anyString());
        verify(tempUserRepository, times(1)).save(any());
    }

    @Test
    void registerTempUser_RegistrationError() {
        // Arrange
        TempUserInputDTO registrationDTO = new TempUserInputDTO();
        registrationDTO.setEmail("test@example.com");
        registrationDTO.setPassword("password");
        registrationDTO.setFirstName("John");
        registrationDTO.setLastName("Doe");
        registrationDTO.setPhoneNumber("1234567890");
        registrationDTO.setScholarNumber("S123456");
        registrationDTO.setIdCardImageUrl("path/to/id/card");

        // Simulate an exception during saveTempUser
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(userRepository.findByScholarNumber(any())).thenReturn(Optional.empty());
        doThrow(new RuntimeException("Simulated exception during saveTempUser")).when(tempUserRepository).save(any());

        // Act
        ResponseEntity<String> response = registrationService.registerTempUser(registrationDTO);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Registration failed"));
    }

    @Test
    void registerTempUser_RegistrationErrorUserAlreadyExists() {
        // Arrange
        TempUserInputDTO registrationDTO = new TempUserInputDTO();
        registrationDTO.setEmail("test@example.com");
        registrationDTO.setPassword("password");
        registrationDTO.setFirstName("John");
        registrationDTO.setLastName("Doe");
        registrationDTO.setPhoneNumber("1234567890");
        registrationDTO.setScholarNumber("S123456");
        registrationDTO.setIdCardImageUrl("path/to/id/card");

        // Simulate an exception during saveTempUser
        when(userRepository.findByEmail(any())).thenReturn(Optional.of(new User()));
        when(userRepository.findByScholarNumber(any())).thenReturn(Optional.empty());


        // Act
        ResponseEntity<String> response = registrationService.registerTempUser(registrationDTO);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("User Already Exists"));
    }

    @Test
    void registerUser_UserNameNotFound() {
        VerificationInputDTO verificationInputDTO = new VerificationInputDTO();
        verificationInputDTO.setEmail("test@example.com");
        verificationInputDTO.setVerificationCode("123456");

        when(tempUserRepository.findByEmail(any())).thenReturn(Optional.empty());

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            registrationService.registerUser(verificationInputDTO);
        });

        assertEquals("Email not found", exception.getMessage());
        RegistrationService mockedRegistrationService = mock(RegistrationService.class);
        verify(mockedRegistrationService, never()).completeUserRegistration(any(TempUser.class));
    }

    @Test
    void registerUser_WrongOTP() {
        VerificationInputDTO verificationInputDTO = new VerificationInputDTO();
        verificationInputDTO.setEmail("test@example.com");
        verificationInputDTO.setVerificationCode("incorrectOTP");

        TempUser tempUser = createSampleTempUser();
        tempUser.setVerificationToken("correctOTP");

        when(tempUserRepository.findByEmail(any())).thenReturn(Optional.of(tempUser));
        when(verificationService.verifyEmail(tempUser, verificationInputDTO.getVerificationCode())).thenReturn(false);

        ResponseEntity<String> response = registrationService.registerUser(verificationInputDTO);


        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Wrong OTP, please try again", response.getBody());
        RegistrationService mockedRegistrationService = mock(RegistrationService.class);
        verify(mockedRegistrationService, never()).completeUserRegistration(any(TempUser.class));
    }



    @Test
    void completeUserRegistration_SuccessfulRegistration() {
        TempUser tempUser = createSampleTempUser();

        when(userRepository.save(any(User.class))).thenReturn(new User());

        ResponseEntity<String> response = registrationService.completeUserRegistration(tempUser);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Registration successful for"));
        verify(userRepository, times(1)).save(any(User.class));
        verify(tempUserRepository, times(1)).delete(any(TempUser.class));
    }

    @Test
    void completeUserRegistration_RegistrationFailed() {
        TempUser tempUser = createSampleTempUser();

        doThrow(new RuntimeException("Simulated exception during save User to Repo")).when(userRepository).save(any());


        ResponseEntity<String> response = registrationService.completeUserRegistration(tempUser);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(Objects.requireNonNull(response.getBody()).contains("Registration failed"));
    }

    private TempUser createSampleTempUser() {
        TempUser tempUser = new TempUser();
        tempUser.setTempUserId(1L);
        tempUser.setEmail("test@example.com");
        tempUser.setPassword("password");
        tempUser.setFirstName("John");
        tempUser.setLastName("Doe");
        tempUser.setPhoneNumber("1234567890");
        tempUser.setScholarNumber("S123456");
        tempUser.setIdCardImageUrl("path/to/id/card");
        tempUser.setRegistrationDate(LocalDate.now());
        tempUser.setVerificationToken("123456");
        tempUser.setVerificationStatus(false);

        return tempUser;
    }

}

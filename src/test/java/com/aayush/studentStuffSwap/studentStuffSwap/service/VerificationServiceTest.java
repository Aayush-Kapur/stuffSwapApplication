package com.aayush.studentStuffSwap.studentStuffSwap.service;

import com.aayush.studentStuffSwap.studentStuffSwap.model.TempUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class VerificationServiceTest {

    @InjectMocks
    private VerificationService verificationService;

    private TempUser sampleTempUser;

    @BeforeEach
    public void setUp() {
        sampleTempUser = createSampleTempUser();
    }

    @Test
    public void testVerifyEmailSuccess() {
        String verificationCode = "123456";
        sampleTempUser.setVerificationToken(verificationCode);
        assertNotNull(sampleTempUser);
        boolean result = verificationService.verifyEmail(sampleTempUser, verificationCode);

        assertTrue(result);
    }

    @Test
    public void testVerifyEmailFailure() {
        String verificationCode = "123456";
        sampleTempUser.setVerificationToken("differentToken"); // Set a different token
        assertNotNull(sampleTempUser);
        boolean result = verificationService.verifyEmail(sampleTempUser, verificationCode);

        assertFalse(result);
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
        tempUser.setVerificationToken("sampleToken");
        tempUser.setVerificationStatus(false);

        return tempUser;
    }
}

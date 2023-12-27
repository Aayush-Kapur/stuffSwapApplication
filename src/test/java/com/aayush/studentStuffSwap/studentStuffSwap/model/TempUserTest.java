package com.aayush.studentStuffSwap.studentStuffSwap.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TempUserTest {

    @Test
    public void testAllArgsConstructorTempUser() {
        // Arrange
        Long tempUserId = 1L;
        String email = "test@example.com";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "1234567890";
        String scholarNumber = "S123456";
        String idCardImageUrl = "path/to/id/card";
        LocalDate registrationDate = LocalDate.now();
        String verificationToken = "abc123";
        boolean verificationStatus = true;

        // Act
        TempUser tempUser = new TempUser(tempUserId, email, password, firstName, lastName,
                phoneNumber, scholarNumber, idCardImageUrl, registrationDate,
                verificationToken, verificationStatus);

        // Assert
        assertEquals(tempUserId, tempUser.getTempUserId());
        assertEquals(email, tempUser.getEmail());
        assertEquals(password, tempUser.getPassword());
        assertEquals(firstName, tempUser.getFirstName());
        assertEquals(lastName, tempUser.getLastName());
        assertEquals(phoneNumber, tempUser.getPhoneNumber());
        assertEquals(scholarNumber, tempUser.getScholarNumber());
        assertEquals(idCardImageUrl, tempUser.getIdCardImageUrl());
        assertEquals(registrationDate, tempUser.getRegistrationDate());
        assertEquals(verificationToken, tempUser.getVerificationToken());
        assertEquals(verificationStatus, tempUser.isVerificationStatus());
    }
}

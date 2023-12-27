package com.aayush.studentStuffSwap.studentStuffSwap.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    public void testNoArgsConstructor() {
        // Arrange
        User user = new User();

        // Act & Assert
        assertNotNull(user);
    }

    @Test
    public void testAllArgsConstructor() {
        // Arrange
        Long userId = 1L;
        String email = "test@example.com";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "1234567890";
        LocalDate registrationDate = LocalDate.now();
        String scholarNumber = "S123456";
        String idCardImageUrl = "path/to/id/card";
        Set<Role> roles = new HashSet<>();
        List<Listing> listings = new ArrayList<>();

        // Act
        User user = new User(userId, email, password, firstName, lastName, phoneNumber,
                registrationDate, scholarNumber, idCardImageUrl, roles, listings);

        // Assert
        assertEquals(userId, user.getUserID());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(phoneNumber, user.getPhoneNumber());
        assertEquals(registrationDate, user.getRegistrationDate());
        assertEquals(scholarNumber, user.getScholarNumber());
        assertEquals(idCardImageUrl, user.getIdCardImageUrl());
        assertEquals(roles, user.getRoles());
        assertEquals(listings, user.getListings());
    }

    @Test
    public void testDataAnnotation() {
        // Arrange
        User user = new User();

        // Act & Assert
        assertNotNull(user);
        // Check that toString, equals, and hashCode methods are generated
        assertTrue(user.toString().contains("User("));
        assertTrue(user.equals(user));
        assertTrue(user.hashCode() != 0);
        // You can add more assertions based on the behavior you expect from Lombok's @Data annotation
    }
}

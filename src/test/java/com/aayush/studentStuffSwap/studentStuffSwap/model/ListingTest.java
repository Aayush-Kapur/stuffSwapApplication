package com.aayush.studentStuffSwap.studentStuffSwap.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListingTest {

    @Test
    public void testNoArgsConstructor() {
        // Arrange
        Listing listing = new Listing();

        // Act & Assert
        assertNotNull(listing);
    }

    @Test
    public void testAllArgsConstructor() {
        // Arrange
        Long listingId = 1L;
        String title = "Test Listing";
        String description = "This is a test listing";
        double price = 19.99;
        User seller = new User();
        String status = "Active";
        LocalDate dateCreated = LocalDate.now();
        Category category = new Category();
        List<String> listingImageUrls = new ArrayList<>();

        // Act
        Listing listing = new Listing(listingId, title, description, price, seller, status, dateCreated, category, listingImageUrls);

        // Assert
        assertEquals(listingId, listing.getListingID());
        assertEquals(title, listing.getTitle());
        assertEquals(description, listing.getDescription());
        assertEquals(price, listing.getPrice());
        assertEquals(seller, listing.getSeller());
        assertEquals(status, listing.getStatus());
        assertEquals(dateCreated, listing.getDateCreated());
        assertEquals(category, listing.getCategory());
        assertEquals(listingImageUrls, listing.getListingImageUrls());
    }

    @Test
    public void testDataAnnotation() {
        // Arrange
        Listing listing = new Listing();

        // Act & Assert
        assertNotNull(listing);
        // Check that toString, equals, and hashCode methods are generated
        assertTrue(listing.toString().contains("Listing("));
        assertTrue(listing.equals(listing));
        assertTrue(listing.hashCode() != 0);
        // You can add more assertions based on the behavior you expect from Lombok's @Data annotation
    }
}

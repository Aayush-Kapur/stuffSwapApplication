package com.aayush.studentStuffSwap.studentStuffSwap.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest {

    @Test
    public void testNoArgsConstructor() {
        Category category = new Category();

        assertNotNull(category);
    }

    @Test
    public void testAllArgsConstructor() {
        Long categoryId = 1L;
        String categoryName = "Test Category";
        List<Listing> listings = new ArrayList<>();

        Category category = new Category(categoryId, categoryName, listings);

        assertEquals(categoryId, category.getCategoryId());
        assertEquals(categoryName, category.getCategoryName());
        assertEquals(listings, category.getListings());
    }

    @Test
    public void testDataAnnotation() {
        Category category = new Category();

        assertNotNull(category);
        assertTrue(category.toString().contains("Category("));
        assertEquals(category, category);
        assertTrue(category.hashCode() != 0);
    }
}

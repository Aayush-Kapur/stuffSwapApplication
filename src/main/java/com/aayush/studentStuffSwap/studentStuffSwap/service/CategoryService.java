package com.aayush.studentStuffSwap.studentStuffSwap.service;

import com.aayush.studentStuffSwap.studentStuffSwap.OutputDataTransferObject.CategoryOutputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.model.Category;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.CategoryRepository;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryService {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<List<String>> addCategories(List<String> categoryList) {
        List<String> addedCategories = new ArrayList<>();

        List<Category> categoriesToSave = new ArrayList<>();

        for (String categoryName : categoryList) {
            if (categoryRepository.findByCategoryName(categoryName).isEmpty()) {
                Category category = new Category();
                category.setCategoryName(categoryName);
                categoriesToSave.add(category);
                addedCategories.add(categoryName);
            }
        }

        if (!categoriesToSave.isEmpty()) {
            try {
                categoryRepository.saveAll(categoriesToSave);
            } catch (DataIntegrityViolationException e) {
               return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return ResponseEntity.ok(addedCategories);
    }


    public ResponseEntity<String> addCategory(String categoryName) {
        if (categoryRepository.findByCategoryName(categoryName).isEmpty()) {
            Category category = new Category();
            category.setCategoryName(categoryName);

            try {
                categoryRepository.save(category);
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.badRequest().body("Failed to add the category " + e);
            }
            return ResponseEntity.ok("Category added successfully");
        }
        else{
            return ResponseEntity.ok("Category already exists");
        }
    }

    public ResponseEntity<String> removeCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return ResponseEntity.ok("deleted bitch category");
    }

    public ResponseEntity<List<CategoryOutputDTO>> getCategories() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryOutputDTO> categoryOutputDTOs = categories.stream()
                .map(CategoryOutputDTO::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(categoryOutputDTOs, HttpStatus.OK);
    }
}

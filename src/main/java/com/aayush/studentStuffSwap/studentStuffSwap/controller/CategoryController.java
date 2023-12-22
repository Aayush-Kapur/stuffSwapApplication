package com.aayush.studentStuffSwap.studentStuffSwap.controller;

import com.aayush.studentStuffSwap.studentStuffSwap.OutputDataTransferObject.CategoryOutputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // TODO : tested for pre-authorization of admin access only using email-id, change it later.
    @PreAuthorize("authentication.principal.username == 'shiprakapurlko@gmail.com'")
    @PostMapping("/add-categories") //TODO : ADMIN ONLY
    public ResponseEntity<List<String>> addCategories(@RequestBody List<String> categoryList) {
        return categoryService.addCategories(categoryList);
    }

    @PreAuthorize("authentication.principal.username == 'shiprakapurlko@gmail.com'")
    @PostMapping("/add-category") //TODO : ADMIN ONLY
    public ResponseEntity<String> addCategory(@RequestBody String category) {
        return categoryService.addCategory(category);
    }

    @PreAuthorize("authentication.principal.username == 'shiprakapurlko@gmail.com'")
    @DeleteMapping("/remove-category") //TODO : ADMIN ONLY
    public ResponseEntity<String> deleteCategory(@RequestParam Long categoryId) {
        return categoryService.removeCategory(categoryId);
    }

    @PreAuthorize("authentication.principal.username == 'shiprakapurlko@gmail.com'")
    @GetMapping("/all-categories")
    public ResponseEntity<List<CategoryOutputDTO>> getCategories() {
        return categoryService.getCategories();
    }
}

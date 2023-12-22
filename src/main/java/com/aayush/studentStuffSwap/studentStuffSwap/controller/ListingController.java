package com.aayush.studentStuffSwap.studentStuffSwap.controller;

import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.ListingInputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.OutputDataTransferObject.ListingOutputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.service.ListingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("listing")
@Slf4j
public class ListingController {
    @Autowired
    ListingService listingService;

    @GetMapping("/category")
    public ResponseEntity<List<ListingOutputDTO>> getListingsUnderCategory(@RequestParam String category) {
        return listingService.getAllListingsForCategory(category);
    }

    @GetMapping("/my-listings")
    public ResponseEntity<List<ListingOutputDTO>> getListingsByCurrentUser() {
        return listingService.getAllListingsByCurrentUser();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ListingOutputDTO>> getAllListingsByUser(@PathVariable Long userId) {
        return listingService.getAllListingsByUser(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ListingOutputDTO>> getAllListings() {
        return listingService.getAllListings();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addListing( @RequestBody ListingInputDTO listingInputDTO) {
        return listingService.postListing(listingInputDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateListing(@RequestParam Long listingId,
                                                @RequestBody ListingInputDTO listingInputDTO) {
        return listingService.updateListing(listingId, listingInputDTO);
    }

}

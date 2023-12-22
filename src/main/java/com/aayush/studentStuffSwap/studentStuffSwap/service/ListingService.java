package com.aayush.studentStuffSwap.studentStuffSwap.service;

import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.ListingInputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.OutputDataTransferObject.ListingOutputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.model.Category;
import com.aayush.studentStuffSwap.studentStuffSwap.model.Listing;
import com.aayush.studentStuffSwap.studentStuffSwap.model.User;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.CategoryRepository;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.ListingRepository;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ListingService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ListingRepository listingRepository;

    public ResponseEntity<String> postListing(ListingInputDTO listingInputDTO) {
        try {
            User user = getCurrentUserFromSession();
            Category category = getCategory(listingInputDTO.getCategoryName());
            Listing listing = createListingFromInputDTO(listingInputDTO, user, category);

            listingRepository.save(listing);
            return new ResponseEntity<>("Listing added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<List<ListingOutputDTO>> getAllListingsForCategory(String category) {
        List<Listing> listings = listingRepository.getListingsByCategory_CategoryName(category);
        List<ListingOutputDTO> listingOutputDTOList = mapListingsToListingOutputDTO(listings);

        return new ResponseEntity<>(listingOutputDTOList, HttpStatus.OK);
    }

    public ResponseEntity<List<ListingOutputDTO>> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        List<ListingOutputDTO> listingOutputDTOList = mapListingsToListingOutputDTO(listings);

        return new ResponseEntity<>(listingOutputDTOList, HttpStatus.OK);
    }

    public ResponseEntity<List<ListingOutputDTO>> getAllListingsByCurrentUser() {
        User user = getCurrentUserFromSession();
        List<Listing> listings = user.getListings();
        List<ListingOutputDTO> listingOutputDTOList = mapListingsToListingOutputDTO(listings);

        return new ResponseEntity<>(listingOutputDTOList, HttpStatus.OK);
    }

    public ResponseEntity<List<ListingOutputDTO>> getAllListingsByUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<ListingOutputDTO> listingOutputDTOList = mapListingsToListingOutputDTO(user.getListings());
            return new ResponseEntity<>(listingOutputDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<String> updateListing(Long listingId, ListingInputDTO listingInputDTO) {
        try {
            User user = getCurrentUserFromSession();
            Listing listing = getListingById(listingId);

            if (isUserAuthorizedToUpdate(user, listing)) {
                Category category = getCategory(listingInputDTO.getCategoryName());
                updateListingFromInputDTO(listing, listingInputDTO, category);

                listingRepository.save(listing);
                return new ResponseEntity<>("Listing updated successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Unauthorized to update the listing", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Exception: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void updateListingFromInputDTO(Listing listing, ListingInputDTO listingInputDTO, Category category) {
        listing.setTitle(listingInputDTO.getTitle());
        listing.setDescription(listingInputDTO.getDescription());
        listing.setListingImageUrls(listingInputDTO.getListingImageUrls());
        listing.setPrice(listingInputDTO.getPrice());
        listing.setCategory(category);
    }
    private User getCurrentUserFromSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return getUserByEmail(userDetails.getUsername());
    }

    private Listing createListingFromInputDTO(ListingInputDTO listingInputDTO, User user, Category category) {
        Listing listing = new Listing();
        listing.setTitle(listingInputDTO.getTitle());
        listing.setDescription(listingInputDTO.getDescription());
        listing.setListingImageUrls(listingInputDTO.getListingImageUrls());
        listing.setPrice(listingInputDTO.getPrice());
        listing.setCategory(category);
        listing.setDateCreated(LocalDateTime.now().toLocalDate());
        listing.setSeller(user);
        return listing;
    }


    protected List<ListingOutputDTO> mapListingsToListingOutputDTO(List<Listing> listings) {
        List<ListingOutputDTO> listingOutputDTOList = new ArrayList<>();
        for (Listing listing : listings) {
            ListingOutputDTO listingOutputDTO = mapListingToListingOutputDTO(listing);
            listingOutputDTOList.add(listingOutputDTO);
        }
        return listingOutputDTOList;
    }

    private ListingOutputDTO mapListingToListingOutputDTO(Listing listing) {
        return new ListingOutputDTO(listing);
    }

    private Category getCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByCategoryName(categoryName);
        return category.orElse(null);
    }

    private User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    private Listing getListingById(Long listingId) {
        Optional<Listing> listing = listingRepository.findById(listingId);
        return listing.orElse(null);
    }
    private boolean isUserAuthorizedToUpdate(User user, Listing listing) {
        return Objects.equals(listing.getSeller().getEmail(), user.getEmail());
    }

}


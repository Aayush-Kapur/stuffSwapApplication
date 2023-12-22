package com.aayush.studentStuffSwap.studentStuffSwap.repository;


import com.aayush.studentStuffSwap.studentStuffSwap.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> getListingsByCategory_CategoryName(String category);
}

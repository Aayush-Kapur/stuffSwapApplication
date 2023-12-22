package com.aayush.studentStuffSwap.studentStuffSwap.OutputDataTransferObject;

import com.aayush.studentStuffSwap.studentStuffSwap.helper.ContactSeller;
import com.aayush.studentStuffSwap.studentStuffSwap.model.Listing;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class ListingOutputDTO {
    private Long listingID;
    private String title;
    private String description;
    private String category;
    private double price;
    private String sellerEmail;
    private LocalDate dateCreated;
    private List<String> listingImageUrls;
    private ContactSeller contactSeller;

    public ListingOutputDTO(Listing listing) {
        this.listingID = listing.getListingID();
        this.title = listing.getTitle();
        this.description = listing.getDescription();
        this.category = listing.getCategory().getCategoryName();
        this.price = listing.getPrice();
        this.sellerEmail = listing.getSeller().getEmail();
        this.dateCreated = listing.getDateCreated();
        this.listingImageUrls = listing.getListingImageUrls();
        this.contactSeller  = new ContactSeller(listing.getSeller().getPhoneNumber());
    }
}

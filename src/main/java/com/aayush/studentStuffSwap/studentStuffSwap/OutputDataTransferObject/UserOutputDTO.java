package com.aayush.studentStuffSwap.studentStuffSwap.OutputDataTransferObject;

import com.aayush.studentStuffSwap.studentStuffSwap.model.User;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserOutputDTO {
    private Long userID;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String scholarNumber;
    private Set<ListingOutputDTO> listings;
    private LocalDate registrationDate;

    public UserOutputDTO(User user) {
        this.userID = user.getUserID();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.scholarNumber = user.getScholarNumber();
        this.registrationDate = user.getRegistrationDate();
        this.listings = user.getListings()
                .stream()
                .map(ListingOutputDTO::new)
                .collect(Collectors.toSet());
    }
}

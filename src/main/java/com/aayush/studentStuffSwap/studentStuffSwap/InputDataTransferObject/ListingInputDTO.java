package com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;


@Data
public class ListingInputDTO {
    @NotBlank(message = "title should not be blank")
    private String title;

    @NotBlank(message = "description should not be blank")
    private String description;

    @NotBlank(message = "category cannot be blank")
    private String categoryName;

    @NotBlank(message = "price should not be blank")
    private double price;

    private List<String> listingImageUrls;
}

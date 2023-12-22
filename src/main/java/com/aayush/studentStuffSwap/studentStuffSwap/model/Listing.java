package com.aayush.studentStuffSwap.studentStuffSwap.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "listings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Listing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listingID;

    private String title;
    private String description;
    private double price;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;

    private String status;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private List<String> listingImageUrls;
}

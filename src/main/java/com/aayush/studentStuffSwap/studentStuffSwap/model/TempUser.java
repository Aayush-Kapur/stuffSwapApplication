package com.aayush.studentStuffSwap.studentStuffSwap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "temp_users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tempUserId;

    @Column(unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "scholar_number")
    private String scholarNumber;

    @Column(name = "id_card")
    private String idCardImageUrl;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "verification_status")
    private boolean verificationStatus;
}

package com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
public class TempUserInputDTO {
    @NotBlank(message = "email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;

    @NotBlank(message = "firstName cannot be blank")
    private String firstName;

    @NotBlank(message = "lastName cannot be blank")
    private String lastName;

    @NotBlank(message = "phoneNumber cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "scholarNumber cannot be blank")
    @Pattern(regexp = "^\\d{2}U0\\d{4}$", message = "invalid scholar number")
    private String scholarNumber;

    private String idCardImageUrl;
}

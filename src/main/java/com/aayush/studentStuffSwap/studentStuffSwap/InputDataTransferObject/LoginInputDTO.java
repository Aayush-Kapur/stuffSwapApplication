package com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginInputDTO {

    @NotBlank
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank
    private String password;
}

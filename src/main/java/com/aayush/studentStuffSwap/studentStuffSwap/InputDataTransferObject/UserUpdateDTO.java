package com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String idCardImageUrl;
    private String phoneNumber;
    private String password;
}

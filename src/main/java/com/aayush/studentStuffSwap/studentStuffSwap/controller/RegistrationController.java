package com.aayush.studentStuffSwap.studentStuffSwap.controller;

import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.LoginInputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.TempUserInputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.VerificationInputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/signup")
    public ResponseEntity<String> temporaryRegister(@RequestBody TempUserInputDTO tempUserInputDTO) {
        return registrationService.registerTempUser(tempUserInputDTO);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestBody VerificationInputDTO verificationInputDTO){
        return registrationService.registerUser(verificationInputDTO);
    }


    //TODO : Implement Login functionality
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginInputDTO loginInputDTO){
        return new ResponseEntity<>("Implemented later using frontend" ,HttpStatus.OK);
    }
}

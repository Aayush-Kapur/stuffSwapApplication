package com.aayush.studentStuffSwap.studentStuffSwap.controller;

import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.UserUpdateDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.OutputDataTransferObject.UserOutputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all") // TODO : ADMIN ACCESSIBLE ONLY
    public ResponseEntity<List<UserOutputDTO>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping()
    public ResponseEntity<UserOutputDTO> getUser() {
        return userService.getCurrentUser();
    }

    @PutMapping("/update")
    public ResponseEntity<UserOutputDTO> updateUser(@RequestBody UserUpdateDTO userUpdateDTO) {
       return userService.updateUser(userUpdateDTO);

    }
}

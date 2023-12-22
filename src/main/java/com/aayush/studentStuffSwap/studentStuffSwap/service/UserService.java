package com.aayush.studentStuffSwap.studentStuffSwap.service;

import com.aayush.studentStuffSwap.studentStuffSwap.InputDataTransferObject.UserUpdateDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.OutputDataTransferObject.UserOutputDTO;
import com.aayush.studentStuffSwap.studentStuffSwap.model.User;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<List<UserOutputDTO>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserOutputDTO> userOutputDTOs = new ArrayList<>();

        for (User user : users) {
            userOutputDTOs.add(mapUserToUserOutputDTO(user));
        }

        return new ResponseEntity<>(userOutputDTOs, HttpStatus.OK);
    }

    public ResponseEntity<UserOutputDTO> getCurrentUser() {
        User userFromSession = getCurrentUserFromSession();
        if (userFromSession != null) {
            return new ResponseEntity<>(mapUserToUserOutputDTO(userFromSession), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<UserOutputDTO> updateUser(UserUpdateDTO userUpdateDTO) {
        User existingUser = getCurrentUserFromSession();
        if (existingUser != null) {
            updateUserData(existingUser, userUpdateDTO);
            User updatedUser = userRepository.save(existingUser);
            UserOutputDTO userOutputDTO = mapUserToUserOutputDTO(updatedUser);
            return new ResponseEntity<>(userOutputDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private User getCurrentUserFromSession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return getUserByEmail(userDetails.getUsername());
    }

    private void updateUserData(User user, UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO.getFirstName() != null) {
            user.setFirstName(userUpdateDTO.getFirstName());
        }
        if (userUpdateDTO.getLastName() != null) {
            user.setLastName(userUpdateDTO.getLastName());
        }
        if (userUpdateDTO.getIdCardImageUrl() != null) {
            user.setIdCardImageUrl(userUpdateDTO.getIdCardImageUrl());
        }
        if (userUpdateDTO.getPhoneNumber() != null) {
            user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        }
        if (userUpdateDTO.getPassword() != null) {
            user.setPassword(userUpdateDTO.getPassword());
        }
    }

    private UserOutputDTO mapUserToUserOutputDTO(User user) {
        return new UserOutputDTO(user);
    }

    private User getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }
}

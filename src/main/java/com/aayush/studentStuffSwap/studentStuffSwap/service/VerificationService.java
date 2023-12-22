package com.aayush.studentStuffSwap.studentStuffSwap.service;

import com.aayush.studentStuffSwap.studentStuffSwap.model.TempUser;
import com.aayush.studentStuffSwap.studentStuffSwap.repository.TempUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VerificationService {

    @Autowired
    private TempUserRepository tempUserRepository;

    public static boolean verifyEmail(TempUser tempUser, String verificationCode) {
        return Objects.equals(tempUser.getVerificationToken(), verificationCode);
    }
}

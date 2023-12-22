package com.aayush.studentStuffSwap.studentStuffSwap.helper;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpGenerator {
    public static String generateOTP(int length) {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }
}

package com.aayush.studentStuffSwap.studentStuffSwap.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendVerificationEmail() {
        String toEmail = "test@example.com";
        String verificationCode = "123456";

        emailService.sendVerificationEmail(toEmail, verificationCode);

        verify(javaMailSender).send(any(SimpleMailMessage.class));
    }
}

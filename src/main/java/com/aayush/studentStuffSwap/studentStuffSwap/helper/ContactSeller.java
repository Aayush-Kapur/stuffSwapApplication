package com.aayush.studentStuffSwap.studentStuffSwap.helper;

import lombok.Data;
import lombok.Getter;

@Data
public class ContactSeller {

    private String phoneCallLink;
    private String whatsappLink;

    public ContactSeller(String phoneNumber) {
        phoneCallLink = generatePhoneCallLink(phoneNumber);
        whatsappLink = generateWhatsAppLink(phoneNumber);
    }

    private String generatePhoneCallLink(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        return "tel:" + phoneNumber;
    }

    private String generateWhatsAppLink(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        return "https://wa.me/" + phoneNumber;
    }

}

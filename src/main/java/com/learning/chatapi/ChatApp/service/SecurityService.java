package com.learning.chatapi.ChatApp.service;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;

@Service
public class SecurityService {

    private final static String HASHING_ALGO = "SHA-256";

    String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASHING_ALGO);
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            return hexToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    String hexToString(byte[] hash) {
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }
}

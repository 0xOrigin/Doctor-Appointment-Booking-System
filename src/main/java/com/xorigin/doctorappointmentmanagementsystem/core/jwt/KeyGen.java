package com.xorigin.doctorappointmentmanagementsystem.core.jwt;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class KeyGen {

    private static final String ALGORITHM = "HmacSHA256";
    private static final Integer KEY_SIZE = 256;

    public static String generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(KEY_SIZE);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    public static void main(String[] args) {
        try {
            String secretKey = generateSecretKey();
            System.out.println("Generated Secret Key: " + secretKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
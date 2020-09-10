package com.clickerclass.course.util;

import org.bouncycastle.util.Strings;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public static String encrypting(String value) {
        String valueEncrypt = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            valueEncrypt = Strings.fromByteArray(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return valueEncrypt;
    }
}

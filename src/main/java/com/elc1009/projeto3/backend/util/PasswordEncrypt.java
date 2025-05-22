package com.elc1009.projeto3.backend.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.util.DigestUtils;

public class PasswordEncrypt {
    
    public static String passwordToMD5Hash(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            return DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            return null;
        } 
    }
}

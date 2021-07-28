/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.turkcell.poc.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author kissoid
 */
public class EncryptionUtil {
    
    private static final String AES = "AES";
    private static final String SECRET = "secret-key";

    private static Key key;
    private static Cipher cipher;
    
    private EncryptionUtil(){
        
    }

private static Key getKey(){
    if(key == null){
        key = new SecretKeySpec(SECRET.getBytes(), AES);
    }
    return key;
}
    
private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException{
    if(cipher == null){
        cipher = Cipher.getInstance(AES);
    }
    return cipher;
}

    public static String encrypt(String value) {
        try {
            getCipher().init(Cipher.ENCRYPT_MODE, getKey());
            return Base64.getEncoder().encodeToString(getCipher().doFinal(value.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException ex) {
            throw new IllegalStateException(ex);
        } 
    }

    public static String decrypt(String value) {
        try {
            getCipher().init(Cipher.DECRYPT_MODE, getKey());
            return new String(getCipher().doFinal(Base64.getDecoder().decode(value)));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException | NoSuchPaddingException ex ) {
            throw new IllegalStateException(ex);
        } 
    }
    
}

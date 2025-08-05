package com.ridetogether.ridetogether.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class AESUtil {
    private static final String AES_KEY_BASE64 = "uRzK2EMb6guLuA1okToESA==";
    private static final SecretKey KEY =
            new SecretKeySpec(Base64.getDecoder().decode(AES_KEY_BASE64), "AES");

    // Text only
    public static String encryptText(String plain) throws Exception {
        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, KEY);
        return Base64.getEncoder().encodeToString(c.doFinal(plain.getBytes(StandardCharsets.UTF_8)));
    }

//    public static String decryptText(String b64cipher) throws Exception {
//        Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
//        c.init(Cipher.DECRYPT_MODE, KEY);
//        byte[] bytes = c.doFinal(Base64.getDecoder().decode(b64cipher));
//        return new String(bytes, StandardCharsets.UTF_8);
//    }

    public static byte[] decryptFileBytes(byte[] encryptedBytes) throws Exception {
        byte[] key = Base64.getDecoder().decode(AES_KEY_BASE64);
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedBytes);
    }
}
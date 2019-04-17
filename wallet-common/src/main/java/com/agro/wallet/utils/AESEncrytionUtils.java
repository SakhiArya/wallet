package com.agro.wallet.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AESEncrytionUtils implements AttributeConverter<String, String> {

    private static final String loggingId = "AESEncrytionUtils";


    private static SecretKeySpec secretKey;
    private static byte[] key;

    //TODO secret must be read from properties file
    private static String secret = "ENC(bW9vgLFwPkZ7MaBgyLilB5yfb4VxdVeu)";

    public static void setKey() {
        if (CommonUtils.isVoid(secret)) {
            //secret = get from property file : "psp.db.passphrase";
            System.out.println(loggingId + "secret : " + secret);
        }
        MessageDigest sha = null;
        try {
            key = secret.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(loggingId + "error as algorithm did not exist " + e);
        } catch (UnsupportedEncodingException e) {
            System.out.println(loggingId + "error as encoding not supported " + e);
        }
    }

    public static String encrypt(String strToEncrypt) {
        try {
            if (CommonUtils.isVoid(strToEncrypt)) {
                return null;
            }
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder()
                .encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println(loggingId + "Error while encrypting: " + e);
        }
        return null;
    }

    public static String decrypt(String strToDecrypt) {
        try {
            if (CommonUtils.isVoid(strToDecrypt)) {
                return null;
            }
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println(loggingId + "Error while decrypting: " + e);
        }
        return null;
    }

    @Override
    public String convertToDatabaseColumn(String strToEncrypt) {
        return encrypt(strToEncrypt);
    }

    @Override
    public String convertToEntityAttribute(String strToDecrypt) {
        return decrypt(strToDecrypt);
    }
}

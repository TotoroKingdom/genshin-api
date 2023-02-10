package com.totoro.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author:lrj
 * @createDate:2022/8/1
 * @description: Rsa工具
 */
public class RsaUtils {

    // Rsa 私钥
    public static String privateKey = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJ1c7khTPs0PN2hj333TWhdX" +
            "72/4zMk8JGpTEBDn5P5sK+MxG1smEZnKqPFPYIA4D/SUfWHqvZYPROnBKlyV5C0Q3LBy19hUk+dwkJLtqUpFdKsNHQBMNXpNpH" +
            "NTH7ejKSPUSg6nIelkyEp+Y6Vkje66m+3esak3SKAsXPUBkW7vAgMBAAECgYEAjriNSL1vdO37E8uZFHaO/CL9/3EI0u6gHGqv" +
            "ZJXdg7854rlUFUwmpHRA23Nhk4jFN89BFIChaCr7FoLiV8x+f8roIL3seSWxqptzz0MNMdgdjNhe5MC92IfEMZIINYAgkrYO26" +
            "+ULsBUHXmCdoRic6TjN42z14mUJF6yBJ1NCfkCQQDw3pW4UWym/Lx8Yk2S/m233v+f7vwNaTuwbpbisrSXJN4kzSmKZaf5oz+P" +
            "eODtxBhyzg6duiG7bTmcqwzNHN8FAkEApz97HQLIeYDSdW0ByZy1mqZzGuJJhQdJhnZcPgKA3TD8zhuUsg/N6+jlrgzxX4ONUL" +
            "G92GAkyMRy/ehkjJFwYwJBAMkXazO4dtdqxQmrIEyKMZuChzNE2BVyN6zyhvG4dGCDHRroVbf/ap9VlSj4qACY6Dhgo382+BZ7" +
            "988+Vq+fXOkCQGFSV1myXOYXSGB8ZiXjwumTIjfqpc17SBWumk6bNlGASBvHgOd8ut8kT0LQ1UQ3UOw8AbT/fWg2qJOHv7rshv" +
            "ECQQCxEkf+5GmIy5/ka33lRc+3zwDqKpsAbQyOImbMEjASbICxwGqcRmGLeRhM3nNpYT8UXRJ9tv5D3lpJVo5XqeDM";

    public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdXO5IUz7NDzdoY99901oXV+9v+MzJPCRqUx" +
            "AQ5+T+bCvjMRtbJhGZyqjxT2CAOA/0lH1h6r2WD0TpwSpcleQtENywctfYVJPncJCS7alKRXSrDR0ATDV6TaRzUx+3oykj1EoOp" +
            "yHpZMhKfmOlZI3uupvt3rGpN0igLFz1AZFu7wIDAQAB";

    /**
     * 私钥解密
     *
     * @param privateKeyString 私钥
     * @param text 待解密的文本
     * @return 解密后的文本
     */
    public static String decryptByPrivateKey(String text) throws Exception {
        return decryptByPrivateKey(privateKey, text);
    }

    /**
     * 公钥解密
     *
     * @param publicKeyString 公钥
     * @param text 待解密的信息
     * @return 解密后的文本
     */
    public static String decryptByPublicKey(String publicKeyString, String text) throws Exception
    {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 私钥加密
     *
     * @param privateKeyString 私钥
     * @param text 待加密的信息
     * @return 加密后的文本
     */
    public static String encryptByPrivateKey(String privateKeyString, String text) throws Exception
    {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 私钥解密
     *
     * @param privateKeyString 私钥
     * @param text 待解密的文本
     * @return 解密后的文本
     */
    public static String decryptByPrivateKey(String privateKeyString, String text) throws Exception
    {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 公钥加密
     *
     * @param publicKeyString 公钥
     * @param text 待加密的文本
     * @return 加密后的文本
     */
    public static String encryptByPublicKey(String publicKeyString, String text) throws Exception
    {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 构建RSA密钥对
     *
     * @return 生成后的公私钥信息
     */
    public static RsaKeyPair generateKeyPair() throws NoSuchAlgorithmException
    {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        return new RsaKeyPair(publicKeyString, privateKeyString);
    }

    /**
     * RSA密钥对对象
     */
    public static class RsaKeyPair
    {
        private final String publicKey;
        private final String privateKey;

        public RsaKeyPair(String publicKey, String privateKey)
        {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey()
        {
            return publicKey;
        }

        public String getPrivateKey()
        {
            return privateKey;
        }
    }
}

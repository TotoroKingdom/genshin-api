package com.totoro.utils;


import org.apache.commons.codec.binary.Base64;
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
    public static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIk1ayCDFaX4QzdP1v68zacjZBNeWUZFn" +
            "c6XOlJnCne0WM3n6jVc/eiO7Z7+7hKzeDFsS7N+MOlALfkMgXB3uxzKz5EoYosW24fQNtOz0A7tQZ3WdmG+XdusxbEIPBMP1YYcI4jy4WR6" +
            "rWWLGf1FLsOf+zZa+oTnOGbnISrtxgI/AgMBAAECgYB807t1ZayBRT6piJpZQJ18bGc0c8aWduldtRmbNCFCzDFqio36JHLfa4DlAJrJKru" +
            "zgwnP74NCAWCRzn3IOzVfW2Y9VRy8/Kwn+YfpV2he5COqZJixxQPePvYaIAwbZWwKZYblilbAQsVPrgu4rrYxC73ecKd+LiGgfn99Cr5maQ" +
            "JBAOutCYZ4OUIlzLip10/Rm7Rf61AUKEZLuFQ6aVg3pP65GqaXpFdMH1i209oNjvQrorVwk1t5tpdSHmUgDxf5h2sCQQCVCorYzyumaTCFL" +
            "kx3ON0k7CDuO9RCrlF7WMuDxBirEzBvsExsO0S0kk5ng4z5u0aFLFoOLMEUqTQVGbNOLGl9AkA4buXiAjXLxhtgeKLrJQkAxxskwL7oHkeU" +
            "O6xwCFsE97QPAi7DDKJQ7u6Jv0dk5nH+umc+t0VmtK48jtGubvzjAkBxm4ujIKhBPUWC/3vCFVJc5rFjZ6+w9Hef8luPoVa8cPAm8tgOWE9" +
            "S5YNmXFyFsD9dHCW5UFwNh7zvqJuJJ0lxAkEAkq3TzeTu0CvmeM5OGuSoPTXgfEVJXD+GoW2lsb4VFYVISuQXfoC5dH2+png0Q7+0Vzvy8Y" +
            "FRNPOrGwwU4mjV4A==";

    public static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJNWsggxWl+EM3T9b+vM2nI2QTXllGRZ3OlzpSZwp3tF" +
            "jN5+o1XP3oju2e/u4Ss3gxbEuzfjDpQC35DIFwd7scys+RKGKLFtuH0DbTs9AO7UGd1nZhvl3brMWxCDwTD9WGHCOI8uFkeq1lixn9RS7Dn" +
            "/s2WvqE5zhm5yEq7cYCPwIDAQAB";

    /**
     * 私钥解密
     *
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

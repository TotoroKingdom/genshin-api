package com.totoro.utils;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
class RsaUtilsTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        RsaUtils.RsaKeyPair rsaKeyPair = RsaUtils.generateKeyPair();
        System.out.println(rsaKeyPair.getPrivateKey());
        System.out.println(rsaKeyPair.getPublicKey());
    }

}
package com.totoro.utils;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
class RsaUtilsTest {

    public static void main(String[] args) throws Exception {

        String s = RsaUtils.decryptByPrivateKey("XbMguwhJzMjjgDZ1GUWab5vCu3LmRzQy/Fw8nsKu90hK06+67+5tw8KzxzMMLaCsIKjU7mrmcxF8N+lMfEWyTsyhKCZHDHszaXxOZQTG2rQ6zTTXqZ4dLw+5TF4oM/xsHFYwK8/urceu5zur4gj4Ju9O0HY0mcZIcIWTa0Kev4o=");
        System.out.println(s);


    }

    void genarate() throws Exception {

        RsaUtils.RsaKeyPair rsaKeyPair = RsaUtils.generateKeyPair();
        System.out.println("私钥："+rsaKeyPair.getPrivateKey());
        System.out.println("公钥："+rsaKeyPair.getPublicKey());

        String encryptPwd = RsaUtils.encryptByPublicKey(rsaKeyPair.getPublicKey(), "123456");
        System.out.println("加密后的密码：" + encryptPwd);

        String pwd = RsaUtils.decryptByPrivateKey(rsaKeyPair.getPrivateKey(), encryptPwd);

        System.out.println("解密后的密码：" +pwd);

    }

}
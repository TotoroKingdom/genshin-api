package com.totoro;

import com.totoro.utils.SecurityUtils;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException {


        String s = SecurityUtils.encryptPassword("123456");
        System.out.println(s);
    }
}


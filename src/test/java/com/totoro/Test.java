package com.totoro;

import com.totoro.utils.RandomUtils;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException {

        for (int i = 0; i < 100; i++) {
            System.out.println(RandomUtils.randomList(10));
        }
    }
}


package com.totoro.utils;

import java.util.Random;

/**
 * @author: totoro
 * @createDate: 2023 02 15 00 07
 * @description:
 **/
public class RandomUtils {

    public static boolean guaranteed(){
        Random random = new Random();
        return random.nextBoolean();
    }
}

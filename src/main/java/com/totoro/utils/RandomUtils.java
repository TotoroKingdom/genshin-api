package com.totoro.utils;

import java.util.Random;

/**
 * @author: totoro
 * @createDate: 2023 02 15 00 07
 * @description:
 **/
public class RandomUtils {

    /**
     * 抛硬币，二分一概率
     * @return
     */
    public static boolean coinToss(){
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * 在列表中随机选一个
     * @return
     */
    public static int randomList(int size){
        Random random = new Random();
        return random.nextInt(size);
    }
}

package com.totoro;

import java.util.Random;

public class Test {
    public static void main(String[] args) {

//        double i = 73;
//        double number = 0.6;
//        for ( i =  73; i<=90; i++){
//
//            System.out.println("第" + i + "抽概率：" + number);
//            number += 6;
//        }


        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int r = random.nextInt(2);
            int r1 = random.nextInt(10);
            System.out.println(r+"--" + r1);

        }


        double random1 = Math.random();

    }
}

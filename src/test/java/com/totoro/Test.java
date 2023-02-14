package com.totoro;

import com.totoro.pojo.Apple;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException {
        Apple apple = new Apple();

        Class<?> aClass = apple.getClass();

        Field id1 = aClass.getDeclaredField("ID");

        System.out.println(id1);


    }
}


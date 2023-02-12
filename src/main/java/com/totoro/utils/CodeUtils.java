package com.totoro.utils;

import cn.hutool.core.lang.UUID;

/**
 * @author: totoro
 * @createDate: 2023 02 12 23 40
 * @description:
 **/
public class CodeUtils {

    public static String getRegisterCode(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}

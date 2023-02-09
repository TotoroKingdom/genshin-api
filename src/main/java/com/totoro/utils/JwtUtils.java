package com.totoro.utils;

import cn.hutool.core.lang.UUID;
import com.totoro.constants.ConfigConstants;
import com.totoro.constants.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
public class JwtUtils {

    // 令牌自定义标识
    private static String header;

    // 令牌秘钥
    private static String secret;

    private static int expireTime;

    public static void setConfigConstants(ConfigConstants config){
        JwtUtils.header = config.getHeader();
        JwtUtils.secret = config.getSecret();
        JwtUtils.expireTime = config.getExpireTime();
    }





}

package com.totoro.constants;

import com.totoro.utils.JwtUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@Data
@Component
public class ConfigConstants {

    // 令牌自定义标识
    @Value("${jwt.header}")
    private String header;

    // 令牌秘钥
    @Value("${jwt.secret}")
    private String secret;

    // 令牌有效期（默认300分钟）
    @Value("${jwt.expireTime}")
    private int expireTime;

    @PostConstruct
    public void init(){
        JwtUtils.setConfigConstants(this);
    }


}

package com.totoro.auth;

import cn.hutool.core.lang.UUID;
import com.totoro.constants.Constants;
import com.totoro.pojo.auth.LoginUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@Service
public class TokenService {

    // 令牌自定义标识
    @Value("${jwt.header}")
    private String header;

    // 令牌秘钥
    @Value("${jwt.secret}")
    private String secret;

    // 令牌有效期（默认300分钟）
    @Value("${jwt.expireTime}")
    private int expireTime;

    /**
     * 新建token
     * @return
     */
    public String createToken(LoginUser loginUser){

        String token = UUID.fastUUID().toString();
        loginUser.setToken(token);
        refreshToken(loginUser);

        Map<String,Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY,token);
        String jwtToken = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        return jwtToken;
    }

    /**
     * 刷新token
     * @param loginUser
     * @return
     */
    public String refreshToken(LoginUser loginUser){

        return null;
    }
}

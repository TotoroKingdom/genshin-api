package com.totoro.auth;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.totoro.constants.Constants;
import com.totoro.pojo.auth.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    protected static final Long MILLIS_SECOND = 1000;

    protected static final Long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 获取用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request){
        String tokenKey = getTokenKey(request);
        if (StrUtil.isNotEmpty(tokenKey)){
            Claims claims = parseToken(tokenKey);
            String uuid = claims.get(Constants.LOGIN_USER_KEY).toString();
            String userKey = getTokenKey(uuid);
            LoginUser loginUser = (LoginUser) redisTemplate.opsForValue().get(userKey);
            return loginUser;
        }
        return null;
    }

    public void removeLoginUser(String token){
        Assert.notEmpty(token,"token不能为空");
        redisTemplate.delete(token);
    }

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

    public void verifyToken(LoginUser loginUser){
        Long expireTime = loginUser.getExpireTime();
        Long currentTime = System.currentTimeMillis();
        String userKey = getTokenKey(loginUser.getToken());
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN){
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新并缓存token
     */
    public void refreshToken(LoginUser loginUser){
        String tokenKey = getTokenKey(loginUser.getToken());

        Long currentTime = System.currentTimeMillis();
        loginUser.setExpireTime(currentTime + expireTime * MILLIS_MINUTE);
        //缓存用户信息
        redisTemplate.opsForValue().set(tokenKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(HttpServletRequest request){
        String token = request.getHeader(header);
        if (StrUtil.isNotEmpty(token)){
            return token;
        }
        return token;
    }

    private String getTokenKey(String token){
        return Constants.LOGIN_USER_KEY + token;
    }

    private Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}

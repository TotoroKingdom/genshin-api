package com.totoro.auth;

import com.totoro.exception.ServiceException;
import com.totoro.pojo.auth.LoginBody;
import com.totoro.pojo.auth.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Slf4j
@Service
public class LoginService{

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private HttpServletRequest request;
    @Resource
    private TokenService tokenService;

    public String login(LoginBody body){


        Authentication authenticate = null;
        try{
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
            authenticate =  authenticationManager.authenticate(token);
        } catch (Exception e){
            if (e instanceof BadCredentialsException){
                throw new ServiceException("账号或密码错误");
            }else {
                throw new ServiceException(e.getMessage());
            }
        }

        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String jwtToken = tokenService.createToken(loginUser);


        return jwtToken;
    }
}

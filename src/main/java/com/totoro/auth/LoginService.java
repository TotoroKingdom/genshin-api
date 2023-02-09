package com.totoro.auth;

import com.totoro.pojo.auth.LoginBody;
import com.totoro.pojo.auth.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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

    public Map login(LoginBody body) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
        Authentication authenticate = authenticationManager.authenticate(token);
        if (ObjectUtils.isEmpty(authenticate)){
            throw new RuntimeException("账号或密码错误");
        }

        LoginUser principal = (LoginUser) authenticate.getPrincipal();

        HttpSession session = request.getSession();
        session.setAttribute("token"+principal.getUser().getId(),principal);

        HashMap<Object, Object> map = new HashMap<>();
        map.put("token","token"+principal.getUser().getId());

        return map;
    }
}

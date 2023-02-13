package com.totoro.auth;

import com.totoro.constants.UserConstants;
import com.totoro.exception.ServiceException;
import com.totoro.pojo.User;
import com.totoro.pojo.auth.LoginUser;
import com.totoro.service.PermissionService;
import com.totoro.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Set<String> permissions = permissionService.findKeyByUserName(username);
        User user = userService.findUserByUserName(username);
        if (UserConstants.NOT_ACTIVATED.equals(user.getStatus())) {
            throw new ServiceException("账号未激活");
        }

        UserDetails loginUser = new LoginUser(user,permissions);
        return loginUser;
    }
}

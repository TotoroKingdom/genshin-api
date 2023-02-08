package com.totoro.auth;

import com.totoro.pojo.User;
import com.totoro.pojo.auth.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String admin = encoder.encode("admin");

        Set<String> permissions = new HashSet<>();
        permissions.add("auth");

        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword(admin);

        UserDetails loginUser = new LoginUser(user,permissions,null);
        return loginUser;
    }
}

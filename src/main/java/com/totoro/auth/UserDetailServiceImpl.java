package com.totoro.auth;

import com.totoro.pojo.User;
import com.totoro.pojo.auth.LoginUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setPassword(admin);

        UserDetails loginUser = new LoginUser(user);
        return loginUser;
    }
}

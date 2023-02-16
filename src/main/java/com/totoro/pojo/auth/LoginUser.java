package com.totoro.pojo.auth;

import cn.hutool.core.util.ObjectUtil;
import com.totoro.pojo.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Data
public class LoginUser implements UserDetails {

    /**
     * 用户
     */
    private User user;

    /**
     * 权限集
     */
    private Set<String> permissions;

    /**
     * 权限集合
     */
    private List<GrantedAuthority> authorities;

    /**
     * jwtToken
     */
    private String token;

    /**
     * 登录有效时间
     */
    private Long expireTime;

    public LoginUser(){}
    public LoginUser(User user, Set<String> permissions){
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (ObjectUtil.isNotNull(authorities)){
            return authorities;
        }
        authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

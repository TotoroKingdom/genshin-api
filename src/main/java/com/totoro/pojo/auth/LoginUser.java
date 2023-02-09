package com.totoro.pojo.auth;

import cn.hutool.core.util.ObjectUtil;
import com.totoro.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    private User user;

    private Set<String> permissions;

    private List<GrantedAuthority> authorities;

    private String token;

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

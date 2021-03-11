package com.example.initial_frame.bean.login;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
         getAuthorities()：获取当前用户对象所具有的角色信息
         getPassword()：获取当前用户对象的密码
         getUsername()：获取当前用户对象的用户名
         isAccountNonExpired()：当前账户是否未过期
         isAccountNonLocked()：当前账户是否锁定
         isCredentialsNonExpired()：当前账户密码是否未过期
         isEnabled()：当前账户是否可用
 */


@Data
@Entity
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//主键生成策略
    private Integer id;

    private String userName;

    private String password;

    private boolean enable;

    private boolean locked;
    @Transient
    private List<Role> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

}

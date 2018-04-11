package com.example.demo.dtos;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserSecurity extends User{
    @Getter
    private String loginId;
    public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.loginId=username;
    }
}

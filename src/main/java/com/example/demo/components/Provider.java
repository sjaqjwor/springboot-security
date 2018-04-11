package com.example.demo.components;

import com.example.demo.dtos.UserDto;
import com.example.demo.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Provider implements AuthenticationProvider {
    @Autowired
    private UserMapper um;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginId = authentication.getName();
        String passwd = authentication.getCredentials().toString();
        return authenticate(loginId, passwd);
    }

    public Authentication authenticate(String loginId, String password) throws AuthenticationException {
        UserDto user = um.getUser(loginId);
        if (user == null) return null;
        return new MyAuthenticaion(loginId, password, AuthorityUtils.createAuthorityList("user"), user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public class MyAuthenticaion extends UsernamePasswordAuthenticationToken {
        private static final long serialVersionUID = 1L;
        UserDto user;

        public MyAuthenticaion(String loginId, String passwd, List<GrantedAuthority> grantedAuthorities, UserDto user) {
            super(loginId, passwd, grantedAuthorities);
            this.user = user;
        }

        public UserDto getUser() {
            return user;
        }

        public void setUser(UserDto user) {
            this.user = user;
        }
    }
}

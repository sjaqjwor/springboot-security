package com.example.demo.services;

import com.example.demo.dtos.UserDto;
import com.example.demo.dtos.UserSecurity;
import com.example.demo.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class LoginUserDetail implements UserDetailsService{

    @Autowired
    private UserMapper um;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDto ud = um.getUser(s);
        Optional.ofNullable(ud).orElseThrow(()->new UsernameNotFoundException("error"));
        return new UserSecurity(ud.getEmail(),ud.getPassword(),AuthorityUtils.createAuthorityList("user"));

    }
}

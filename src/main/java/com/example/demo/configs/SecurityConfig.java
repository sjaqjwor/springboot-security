package com.example.demo.configs;

import com.example.demo.components.Provider;
import com.example.demo.services.LoginUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Provider p;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 외부 접근 차단
                // same origin policy - > 만약 ajax 호출시 같은 도메인 에만 request 가능
                //preflightrequest는 외부 도메인 에서 호출이 가능 한지 먼저 options 메서드로 요청을 먼저
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                // 해당 패턴은 모두가 접근 가능
                .antMatchers("/login", "/error").permitAll()
                //권한이 있는 사용자만 접근 가능
                .antMatchers("/index").authenticated();
        http.csrf().disable();
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login-process")
                .failureUrl("/error")
                .defaultSuccessUrl("/index", true)
                .usernameParameter("id")
                .passwordParameter("pass");

        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout_processing"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);
        http.sessionManagement()
                .maximumSessions(1)
                .expiredUrl("/error");

//        http.authenticationProvider(p);
    }



        @Configuration
    public static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
            @Autowired
            LoginUserDetail userDetailsService;
            @Bean
            PasswordEncoder passwordEncoder(){
                return new BCryptPasswordEncoder();
            }

            @Override
            public void init(AuthenticationManagerBuilder auth) throws Exception {
                System.err.println(auth.toString());
                auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
            }
        }

}


package com.jn.config;

import com.jn.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.Assert;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    LoginService loginService;
    @Autowired
    private MessageSource messageSource;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(daoAuthenticationProvider())
                .build();
        return authenticationManager;
    }
    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        Assert.notNull(this.loginService, "請實作UserDetailsService");
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(this.loginService);
        impl.setPasswordEncoder(this.passwordEncoder());
        impl.setHideUserNotFoundExceptions(false);
        impl.setMessageSource(this.messageSource);
        return impl;
    }



}

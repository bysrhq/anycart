/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.config.sec;

import com.github.bysrhq.anycart.repository.UserRepository;
import com.github.bysrhq.anycart.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author bysrhq
 */
@Configuration
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .usernameParameter("username").passwordParameter("passcode")
                .loginPage("/user/login")
                .defaultSuccessUrl("/")
                .and()
                .rememberMe().tokenValiditySeconds(600)
                .and()
                .authorizeRequests()
                .antMatchers("/item/**").hasAnyRole("Sales")
                .antMatchers("/cart").hasAnyRole("Sales")
                .antMatchers("/checkout").hasAnyRole("Sales")
                .antMatchers("/transaction/**").hasAnyRole("Cashier", "Owner")
                .anyRequest().permitAll()
                .and()
                .logout().logoutUrl("/user/logout")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsServiceImpl(userRepository));
    }
    
}

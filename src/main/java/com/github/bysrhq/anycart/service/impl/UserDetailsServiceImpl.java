/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service.impl;

import com.github.bysrhq.anycart.domain.Role;
import com.github.bysrhq.anycart.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bysrhq
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private UserRepository userRepository;
    
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.github.bysrhq.anycart.domain.User user = userRepository.getUser(username);
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role : user.getRoles())
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        
        return new User(user.getUsername(), user.getPasscode(), authorities);
    }
    
}

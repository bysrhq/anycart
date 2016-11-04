/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service.impl;

import com.github.bysrhq.anycart.domain.User;
import com.github.bysrhq.anycart.repository.UserRepository;
import com.github.bysrhq.anycart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bysrhq
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    @Override
    public User getUser(String username) {
        return userRepository.getUser(username);
    }
    
}

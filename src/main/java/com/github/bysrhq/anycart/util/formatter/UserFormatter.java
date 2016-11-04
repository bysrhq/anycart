/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.util.formatter;

import com.github.bysrhq.anycart.domain.User;
import com.github.bysrhq.anycart.service.UserService;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author bysrhq
 */
@Component
public class UserFormatter implements Formatter<User> {
    
    @Autowired
    private UserService userService;
    
    @Override
    public String print(User object, Locale locale) {
        return object.getUsername();
    }

    @Override
    public User parse(String text, Locale locale) throws ParseException {
        return userService.getUser(text);
    }
    
}

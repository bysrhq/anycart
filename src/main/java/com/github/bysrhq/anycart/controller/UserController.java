/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.controller;

import com.github.bysrhq.anycart.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bysrhq
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String showLoginForm(User user) {
        return "loginForm";
    }
}
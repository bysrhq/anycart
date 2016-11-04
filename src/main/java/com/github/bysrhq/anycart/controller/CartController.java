/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.controller;

import com.github.bysrhq.anycart.domain.Transaction;
import com.github.bysrhq.anycart.domain.User;
import com.github.bysrhq.anycart.service.TransactionService;
import com.github.bysrhq.anycart.util.validator.TransactionDetailValidator;
import com.github.bysrhq.anycart.util.validator.TransactionValidator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author bysrhq
 */
@Controller
@RequestMapping("/cart")
@SessionAttributes("transaction")
public class CartController {
    
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionValidator transactionValidator;
    @Autowired
    private TransactionDetailValidator transactionDetailValidator;
    
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addTransactionDetailIntoCart(Authentication auth, @RequestParam String itemId, @RequestParam(defaultValue = "1") int itemQty, Transaction transaction) {
        transaction.setSales(new User(auth.getName()));
        
        transactionService.addTransactionDetailIntoTransaction(transaction, itemId, itemQty);
        
        return "redirect:/cart";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String showCart(Authentication auth, Transaction transaction) {
        transaction.setSales(new User(auth.getName()));

        return "cartForm";
    }
    
    @RequestMapping(params = "update", method = RequestMethod.POST)
    public String updateTransactionDetailInCart(@RequestParam("update") int idx, @Valid Transaction transaction, BindingResult errors) {
        transactionDetailValidator.validate(transaction, errors);
        if (errors.hasErrors())
            return "cartForm";
        transactionService.updateTransactionDetailInTransaction(transaction, idx);
        
        return "redirect:/cart";
    }
    
    @RequestMapping(params = "delete", method = RequestMethod.POST)
    public String deleteTransactionDetailFromCart(@RequestParam("delete") int idx, @Valid Transaction transaction, BindingResult errors) {
        transactionDetailValidator.validate(transaction, errors);
        if (errors.hasErrors())
            return "cartForm";
        transactionService.deleteTransactionDetailFromTransaction(transaction, idx);
        
        return "redirect:/cart";
    }
    
    @RequestMapping(params = "checkout", method = RequestMethod.POST)
    public String checkoutCart(SessionStatus status, @Valid Transaction transaction, BindingResult errors) {
        transactionDetailValidator.validate(transaction, errors);
        transactionValidator.validate(transaction, errors);
        if (errors.hasErrors())
            return "cartForm";
        
        status.setComplete();
        transactionService.checkoutTransaction(transaction);
        
        return "redirect:/checkout?id=" + transaction.getId();
    }
    
    @RequestMapping(params = "cancel", method = RequestMethod.POST)
    public String cancelCart(SessionStatus status, Transaction transaction) {
        status.setComplete();
        transactionService.cancelTransaction(transaction.getId());
        
        return "redirect:/item";
    }
    
}

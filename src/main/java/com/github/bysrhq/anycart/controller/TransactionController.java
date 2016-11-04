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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/transaction")
@SessionAttributes("transaction")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private TransactionValidator transactionValidator;
    @Autowired
    private TransactionDetailValidator transactionDetailValidator;
    
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String processShowTransaction(Authentication auth, @PathVariable String id, Model model) {
        Transaction transaction = transactionService.getTransaction(id);
        transaction.setCashier(transaction.getCashier() == null ? new User(auth.getName()) : transaction.getCashier());
        
        model.addAttribute("transaction", transaction);
        
        return "transactionForm";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String processShowTransactionList(@RequestParam(defaultValue = "0") int min, @RequestParam(defaultValue = "15") int count, Model model) {
        model.addAttribute("transactions", transactionService.getTransactions(min, count));
        model.addAttribute("min", min);
        model.addAttribute("count", count);
        
        return "transactionList";
    }
    
    @RequestMapping(value = "{id}", params = "update", method = RequestMethod.POST)
    public String processUpdateTransactionDetailInTransaction(@RequestParam("update") int idx, @Valid Transaction transaction, BindingResult errors) {
        transactionDetailValidator.validate(transaction, errors);
        if (errors.hasErrors() || transaction.isStatus() == true)
            return "transactionForm";
        
        transactionService.updateTransactionDetailInTransaction(transaction, idx);
        
        return "transactionForm";
    }
    
    @RequestMapping(value = "{id}", params = "delete", method = RequestMethod.POST)
    public String processDeleteTransactionDetailFromTransaction(@RequestParam("delete") int idx, @Valid Transaction transaction, BindingResult errors) {
        if (transaction.isStatus() == true)
            transactionService.deleteTransactionDetailFromTransaction(transaction, idx);
        
        return "transactionForm";
    }
    
    @RequestMapping(value = "{id}", params = "commit", method = RequestMethod.POST)
    public String processCheckoutTransaction(SessionStatus status, @Valid Transaction transaction, BindingResult errors) {
        transactionDetailValidator.validate(transaction, errors);
        transactionValidator.validate(transaction, errors);
        if(errors.hasErrors() || transaction.isStatus() == true)
            return "transactionForm";
        
        status.setComplete();
        
        transactionService.commitTransaction(transaction);
        
        return "redirect:/transaction";
    }
    
    @RequestMapping(value = "{id}", params = "cancel", method = RequestMethod.POST)
    public String processCancelTransaction(SessionStatus status, Transaction transaction) {
        if (transaction.isStatus() == true)
            return "transactionForm";
        
        status.setComplete();
        
        transactionService.cancelTransaction(transaction.getId());
        
        return "redirect:/transaction";
    }
}

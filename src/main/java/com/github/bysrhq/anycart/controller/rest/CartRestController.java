/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.controller.rest;

import com.github.bysrhq.anycart.domain.Transaction;
import com.github.bysrhq.anycart.service.TransactionService;
import com.github.bysrhq.anycart.util.msg.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bysrhq
 */
@RestController
@RequestMapping("/rest/cart")
public class CartRestController {
    
    @Autowired
    private TransactionService transactionService;
    
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(params = "itemId", method = RequestMethod.GET)
    public Transaction addTransactionDetailIntoCart(
            @RequestParam String itemId,
            @RequestParam(defaultValue = "1") int itemQty,
            @RequestParam(defaultValue = "") String transactionId) {
        Transaction transaction = StringUtils.isEmpty(transactionId) ?
                new Transaction() :
                transactionService.getTransaction(transactionId);
        
        transactionService.addTransactionDetailIntoTransaction(transaction, itemId, itemQty);
        
        return transaction;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public Transaction showCart(@RequestParam(defaultValue = "") String transactionId) {
        Transaction transaction = null;
        
        System.out.println("passing it : " + transactionId );
        if (StringUtils.isEmpty(transactionId)) {
            transaction = new Transaction();
            transactionService.saveTransaction(transaction);
        } else {
            transaction = transactionService.getTransaction(transactionId);
        }
        
        return transaction;
    }
    
    @RequestMapping(params = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Transaction updateTransactionDetailInCart(@RequestParam("update") int idx, @RequestBody Transaction transaction) {
        transactionService.updateTransactionDetailInTransaction(transaction, idx);
        
        return transaction;
    }
    
    @RequestMapping(params = "delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Transaction deleteTransactionDetailFromCart(@RequestParam("delete") int idx, @RequestBody Transaction transaction) {
        transactionService.deleteTransactionDetailFromTransaction(transaction, idx);
        
        return transaction;
    }
    
    @RequestMapping(params = "checkout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Message checkoutTransaction(@RequestBody Transaction transaction) {
        String id = transaction.getId();
        
        transactionService.checkoutTransaction(transaction);
        
        return new Message(id, "Transaction ID : " + id + "has been checked out");
    }
    
    @RequestMapping(params = "cancel", method = RequestMethod.DELETE)
    public Message cancelTransaction(@RequestParam String transactionId) {
        transactionService.cancelTransaction(transactionId);
        
        return new Message("a message", "Transaction has been canceled");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service.impl;

import com.github.bysrhq.anycart.domain.Item;
import com.github.bysrhq.anycart.domain.Transaction;
import com.github.bysrhq.anycart.domain.TransactionDetail;
import com.github.bysrhq.anycart.repository.ItemRepository;
import com.github.bysrhq.anycart.repository.TransactionRepository;
import com.github.bysrhq.anycart.service.CartService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bysrhq
 */
@Service
public class CartServiceImpl implements CartService{
    
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ItemRepository itemRepository;
    
    @Transactional
    @Override
    public void checkoutCart(Transaction transaction) {
        transactionRepository.updateTransaction(transaction);
    }
    
    @Transactional
    @Override
    public void addTransactionDetailInToCart(Transaction transaction, String itemId, int qty) {
        Item item = itemRepository.getItem(itemId);
        TransactionDetail transactionDetail = new TransactionDetail();
        
        double subTotal = item.getPrice() * qty;
        
        transactionDetail.setItem(item);
        transactionDetail.setQuantity(qty);
        transactionDetail.setSubTotal(subTotal);
        transaction.getTransactionDetails().add(transactionDetail);
        
        double total = transaction.getTotal();
        total += subTotal;
        
        transaction.setTotal(total);
        
        if (StringUtils.isEmpty(transaction.getId()))
            transactionRepository.saveTransaction(transaction);
        else
            transactionRepository.updateTransaction(transaction);
    }

    @Transactional
    @Override
    public void updateTransactionDetailInCart(Transaction transaction, int index) {
        TransactionDetail transactionDetail = transaction.getTransactionDetails().get(index);
        double subTotal = transactionDetail.getSubTotal();
        double total = transaction.getTotal();
        
        total -= subTotal;
        subTotal = transactionDetail.getQuantity() * transactionDetail.getItem().getPrice();
        total += subTotal;
        
        transactionDetail.setSubTotal(subTotal);
        transaction.setTotal(total);
        
        transactionRepository.updateTransaction(transaction);
    }

    @Transactional
    @Override
    public void deleteTransactionDetailFromCart(Transaction transaction, int index) {
        TransactionDetail transactionDetail = transaction.getTransactionDetails().get(index);
        double subTotal = transactionDetail.getSubTotal();
        double total = transaction.getTotal();
        
        total -= subTotal;
        
        transaction.getTransactionDetails().remove(transactionDetail);
        transaction.setTotal(total);
        
        transactionRepository.updateTransaction(transaction);
        
    }

    @Transactional
    @Override
    public void cancelCart(Transaction transaction) {
        Transaction deletedTransaction = transactionRepository.getTransaction(transaction.getId());
        
        transactionRepository.deleteTransaction(deletedTransaction);
    }
    
}

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
import com.github.bysrhq.anycart.service.TransactionService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bysrhq
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ItemRepository itemRepository;
    
    @Transactional
    @Override
    public void addTransactionDetailIntoTransaction(Transaction transaction, String itemId, int itemQty) {
        Item item = itemRepository.getItem(itemId);
        int price = item.getPrice();
        double subTotal = price * itemQty;
        
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setItem(item);
        transactionDetail.setQuantity(itemQty);
        transactionDetail.setSubTotal(subTotal);
        transaction.getTransactionDetails().add(transactionDetail);
        
        double total = transaction.getTotal();
        
        total += subTotal;
        
        transaction.setTotal(total);
        
        if (transaction.getId() == null)
            transactionRepository.saveTransaction(transaction);
        else
            transactionRepository.updateTransaction(transaction);
    }
    
    @Transactional
    @Override
    public void updateTransactionDetailInTransaction(Transaction transaction, int idx) {
        TransactionDetail transactionDetail = transaction.getTransactionDetails().get(idx);
        double price = transactionDetail.getItem().getPrice();
        double quantity = transactionDetail.getQuantity();
        double subTotal = transactionDetail.getSubTotal();
        double total = transaction.getTotal();
        
        total -= subTotal;
        subTotal = price * quantity;
        total += subTotal;
        
        transactionDetail.setSubTotal(subTotal);
        transaction.setTotal(total);
        
        transactionRepository.updateTransaction(transaction);
    }
    
    @Transactional
    @Override
    public void deleteTransactionDetailFromTransaction(Transaction transaction, int idx) {
        TransactionDetail transactionDetail = transaction.getTransactionDetails().get(idx);
        double subTotal = transactionDetail.getSubTotal();
        double total = transaction.getTotal();
        
        total -= subTotal;
        
        transaction.getTransactionDetails().remove(transactionDetail);
        transaction.setTotal(total);
        
        transactionRepository.updateTransaction(transaction);
    }
    
    @Transactional
    @Override
    public void checkoutTransaction(Transaction transaction) {
        transaction.setDate(new Date());
        
        transactionRepository.updateTransaction(transaction);
    }

    @Transactional
    @Override
    public void commitTransaction(Transaction transaction) {
        transaction.setStatus(true);
        
        for (TransactionDetail transactionDetail : transaction.getTransactionDetails()) {
            Item item = itemRepository.getItem(transactionDetail.getItem().getId());
            item.setQuantity(item.getQuantity() - transactionDetail.getQuantity());
        }
        transactionRepository.updateTransaction(transaction);
    }
    
    @Transactional
    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepository.saveTransaction(transaction);
    }
    
    @Transactional
    @Override
    public Transaction getTransaction(String id) {
        return transactionRepository.getTransaction(id);
    }

    
    @Transactional
    @Override
    public List<Transaction> getTransactions(int min, int count) {
        return transactionRepository.getTransactions(min, count);
    }

    @Transactional
    @Override
    public void updateTransaction(Transaction transaction) {
        transactionRepository.updateTransaction(transaction);
    }
    
    @Transactional
    @Override
    public void cancelTransaction(String id) {
        Transaction transaction = transactionRepository.getTransaction(id);
        transactionRepository.deleteTransaction(transaction);
    }
   
}

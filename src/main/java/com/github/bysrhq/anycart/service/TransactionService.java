/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service;

import com.github.bysrhq.anycart.domain.Transaction;
import java.util.List;

/**
 *
 * @author bysrhq
 */
public interface TransactionService {
    void addTransactionDetailIntoTransaction(Transaction transaction, String itemId, int itemQy);
    void updateTransactionDetailInTransaction(Transaction transaction, int idx);
    void deleteTransactionDetailFromTransaction(Transaction transaction, int idx);
    void checkoutTransaction(Transaction transaction);
    void commitTransaction(Transaction transaction);
    Transaction getTransaction(String id);
    void saveTransaction(Transaction transaction);
    List<Transaction> getTransactions(int min, int count);
    void updateTransaction(Transaction transaction);
    void cancelTransaction(String id);
}

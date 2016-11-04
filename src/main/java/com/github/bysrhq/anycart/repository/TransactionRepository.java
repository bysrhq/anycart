/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.repository;

import com.github.bysrhq.anycart.domain.Transaction;
import java.util.List;

/**
 *
 * @author bysrhq
 */
public interface TransactionRepository {
    void saveTransaction(Transaction transaction);
    Transaction getTransaction(String id);
    List<Transaction> getTransactions(int min, int count);
    void updateTransaction(Transaction transaction);
    void deleteTransaction(Transaction transaction);
}

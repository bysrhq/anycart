/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.repository.impl;

import com.github.bysrhq.anycart.domain.Transaction;
import com.github.bysrhq.anycart.repository.TransactionRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bysrhq
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void saveTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().save(transaction);
    }

    @Override
    public Transaction getTransaction(String id) {
        return (Transaction) sessionFactory.getCurrentSession().get(Transaction.class, id);
    }

    @Override
    public List<Transaction> getTransactions(int min, int count) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Transaction")
                .setFirstResult(min).setMaxResults(count)
                .list();
    }
    
    @Override
    public void updateTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().saveOrUpdate(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        sessionFactory.getCurrentSession().delete(transaction);
    }
    
}

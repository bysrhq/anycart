/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service;

import com.github.bysrhq.anycart.domain.Transaction;

/**
 *
 * @author bysrhq
 */
public interface CartService {
    void checkoutCart(Transaction transaction);
    void addTransactionDetailInToCart(Transaction transaction, String itemId, int qty);
    void updateTransactionDetailInCart(Transaction transaction, int index);
    void deleteTransactionDetailFromCart(Transaction transaction, int index);
    void cancelCart(Transaction transaction);
}

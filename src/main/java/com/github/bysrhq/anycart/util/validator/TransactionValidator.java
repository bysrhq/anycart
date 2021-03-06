/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.util.validator;

import com.github.bysrhq.anycart.domain.Transaction;
import com.github.bysrhq.anycart.domain.TransactionDetail;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author bysrhq
 */
@Component
public class TransactionValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Transaction.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Transaction transaction = (Transaction) target;
        double existingTotal = transaction.getTotal();
        double currentTotal = 0;
        for (TransactionDetail transactionDetail : transaction.getTransactionDetails())
            currentTotal = currentTotal + (transactionDetail.getQuantity() * transactionDetail.getItem().getPrice());
    
        if (existingTotal != currentTotal) {
            errors.reject("disbanded");
        }
    }
    
}

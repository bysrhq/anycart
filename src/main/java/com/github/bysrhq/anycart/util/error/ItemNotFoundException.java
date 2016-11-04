/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.util.error;

/**
 *
 * @author bysrhq
 */
public class ItemNotFoundException extends RuntimeException {
    private String itemId;

    public ItemNotFoundException(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }
    
}

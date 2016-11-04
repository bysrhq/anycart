/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.util.formatter;

import com.github.bysrhq.anycart.domain.Item;
import com.github.bysrhq.anycart.service.ItemService;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author bysrhq
 */
@Component
public class ItemFormatter implements Formatter<Item> {
    
    @Autowired
    private ItemService itemService;

    @Override
    public String print(Item object, Locale locale) {
        return object.getId();
    }

    @Override
    public Item parse(String text, Locale locale) throws ParseException {
        return itemService.getItem(text);
    }
    
}

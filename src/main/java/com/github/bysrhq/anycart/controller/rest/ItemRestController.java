/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.controller.rest;

import com.github.bysrhq.anycart.domain.Item;
import com.github.bysrhq.anycart.service.ItemService;
import com.github.bysrhq.anycart.util.error.ItemNotFoundException;
import com.github.bysrhq.anycart.util.error.msg.Message;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/rest/item")
public class ItemRestController {
    
    @Autowired
    private ItemService itemService;
    
    @RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Item item(@PathVariable String id) {
        return itemService.getItem(id);
    }
    
    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public List<Item> getItems(@RequestParam(defaultValue = "0") int min, @RequestParam(defaultValue = "15") int count, @RequestParam(defaultValue = "") String query) {
        return itemService.getItems(min, count, query);
    }
    
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Message getMessage(ItemNotFoundException e) {
        return new Message("404", "Item " + e.getItemId() + "is not found");
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service;

import com.github.bysrhq.anycart.domain.Item;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author bysrhq
 */
public interface ItemService {
    void addItem(Item item, MultipartFile file);
    Item getItem(String id);
    List<Item> getItems(int min, int count, String query);
    void editItem(Item item, MultipartFile file);
    void deleteItem(String id);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.repository;

import com.github.bysrhq.anycart.domain.Item;
import java.util.List;

/**
 *
 * @author bysrhq
 */
public interface ItemRepository {
    void saveItem(Item item);
    Item getItem(String id);
    List<Item> getItems(int min, int count);
    List<Item> getItemsByName(int min, int count, String name);
    List<Item> getItemsBySize(int min, int count, String sizeId);
    List<Item> getItemsByColor(int min, int count, String colorId);
    List<Item> getItemsByBrand(int min, int count, String brandId);
    List<Item> getItemsByCategory(int min, int count, String categoryId);
    void updateItem(Item item);
    void deleteItem(Item item);
}

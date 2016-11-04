/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service.impl;

import com.github.bysrhq.anycart.domain.Item;
import com.github.bysrhq.anycart.service.ItemService;
import com.github.bysrhq.anycart.repository.ItemRepository;
import com.github.bysrhq.anycart.util.error.ItemNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author bysrhq
 */
@Service
public class ItemServiceImpl implements ItemService {
    
    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    @Override
    public void addItem(Item item, MultipartFile file) {
        itemRepository.saveItem(item);
        item.setImage(item.getId() + "." + StringUtils.split(file.getContentType(), "/")[1]);        
        File imageFile = new File("c:/image/" + item.getImage());
        try {
            file.transferTo(imageFile);
        } catch (IOException exc) { }
    }

    @Transactional
    @Override
    public Item getItem(String id) {
        Item item = itemRepository.getItem(id);
        if (item == null)
            throw new ItemNotFoundException(id);
        
        return itemRepository.getItem(id);
    }

    @Transactional
    @Override
    public List<Item> getItems(int min, int count, String query) {
        List<Item> items;
        if (!StringUtils.isBlank(query)) {
            String queryArray[] = StringUtils.split(query, "+ ");
            switch (queryArray[0]) {
                case "name":
                    items = itemRepository.getItemsByName(min, count, queryArray[1]);
                    break;
                case "size":
                    items = itemRepository.getItemsBySize(min, count, queryArray[1]);
                    break;
                case "color":
                    items = itemRepository.getItemsByColor(min, count, queryArray[1]);
                    break;
                case "brand":
                    items = itemRepository.getItemsByBrand(min, count, queryArray[1]);
                    break;
                case "category":
                    items = itemRepository.getItemsByCategory(min, count, queryArray[1]);
                    break;
                default:
                    items = itemRepository.getItems(min, count);
                    break;
            }
        } else {
            items = itemRepository.getItems(min, count);
        }
        
        return items;
    }

    @Transactional
    @Override
    public void editItem(Item item, MultipartFile file) {
        itemRepository.updateItem(item);
        
        File currentImageFile = new File("c:/image/" + item.getImage());
        System.out.println(file.isEmpty());
        System.out.println(item.getId());
        System.out.println(item.getImage());
        if (!file.isEmpty()) {
            
            if(currentImageFile.delete()) {
                try {
                    System.out.println(item.getId());
                
                    File uploadedImageFile = new File("c:/image/" + item.getId() + "." + StringUtils.split(file.getContentType(), "/")[1]);
                    file.transferTo(uploadedImageFile);
                    } catch (IOException exc) { }
                }
            }
    }

    @Transactional
    @Override
    public void deleteItem(String id) {
        Item item = itemRepository.getItem(id);
        if (item == null)
            throw new ItemNotFoundException(id);
        
        File imageFile = new File("c:/image/" + item.getImage());
        imageFile.delete();
        itemRepository.deleteItem(item);
    }
    
}

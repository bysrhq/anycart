/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.repository.impl;

import com.github.bysrhq.anycart.domain.Item;
import com.github.bysrhq.anycart.repository.ItemRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bysrhq
 */
@Repository
public class ItemRepositoryImpl implements ItemRepository {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public void saveItem(Item item) {
        sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public Item getItem(String id) {
        return (Item) sessionFactory.getCurrentSession().get(Item.class, id);
    }

    @Override
    public List<Item> getItems(int min, int count) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Item")
                .setFirstResult(min).setMaxResults(count)
                .list();
    }
    
    @Override
    public List<Item> getItemsBySize(int min, int count, String sizeId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Item where size_id=:sizeId")
                .setFirstResult(min).setMaxResults(count)
                .setString("sizeId", sizeId)
                .list();
    }

    @Override
    public List<Item> getItemsByColor(int min, int count, String colorId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Item where color_id=:colorId")
                .setFirstResult(min).setMaxResults(count)
                .setString("colorId", colorId)
                .list();
    }

    @Override
    public List<Item> getItemsByBrand(int min, int count, String brandId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Item where brand_id=:brandId")
                .setFirstResult(min).setMaxResults(count)
                .setString("brandId", brandId)
                .list();
    }

    @Override
    public List<Item> getItemsByCategory(int min, int count, String categoryId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Item where category_id=:categoryId")
                .setFirstResult(min).setMaxResults(count)
                .setString("categoryId", categoryId)
                .list();
    }
    
    @Override
    public void updateItem(Item item) {
        sessionFactory.getCurrentSession().update(item);
    }

    @Override
    public void deleteItem(Item item) {
        sessionFactory.getCurrentSession().delete(item);
    }

    @Override
    public List<Item> getItemsByName(int min, int count, String name) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Item where name like :name")
                .setFirstResult(min).setMaxResults(count)
                .setString("name", "%" + name + "%")
                .list();
    }
    
}

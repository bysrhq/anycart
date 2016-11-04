/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.repository.impl;

import com.github.bysrhq.anycart.domain.Category;
import com.github.bysrhq.anycart.repository.CategoryRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bysrhq
 */
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Category getCategory(String id) {
        return (Category) sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    public List<Category> getCategories() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Category").list();
    }
    
}

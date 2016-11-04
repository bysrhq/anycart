/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service.impl;

import com.github.bysrhq.anycart.domain.Category;
import com.github.bysrhq.anycart.repository.CategoryRepository;
import com.github.bysrhq.anycart.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bysrhq
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Transactional
    @Override
    public Category getCategory(String id) {
        return categoryRepository.getCategory(id);
    }

    @Transactional
    @Override
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }
    
}

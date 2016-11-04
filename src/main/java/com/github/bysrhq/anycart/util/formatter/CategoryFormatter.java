/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.util.formatter;

import com.github.bysrhq.anycart.domain.Category;
import com.github.bysrhq.anycart.service.CategoryService;
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
public class CategoryFormatter implements Formatter<Category> {
    @Autowired
    private CategoryService categoryService;
    
    @Override
    public String print(Category object, Locale locale) {
        return object != null ? object.getId() : "";
    }

    @Override
    public Category parse(String text, Locale locale) throws ParseException {
        return categoryService.getCategory(text);
    }
    
}

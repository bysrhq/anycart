/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.repository.impl;

import com.github.bysrhq.anycart.domain.Brand;
import com.github.bysrhq.anycart.repository.BrandRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bysrhq
 */
@Repository
public class BrandRepositoryImpl implements BrandRepository {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Brand getBrand(String id) {
        return (Brand) sessionFactory.getCurrentSession().get(Brand.class, id);
    }

    @Override
    public List<Brand> getBrands() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Brand").list();
    }
    
    
}

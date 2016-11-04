/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.repository.impl;

import com.github.bysrhq.anycart.domain.Size;
import com.github.bysrhq.anycart.repository.SizeRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bysrhq
 */
@Repository
public class SizeRepositoryImpl implements SizeRepository {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Size getSize(String id) {
        return (Size) sessionFactory.getCurrentSession().get(Size.class, id);
    }

    @Override
    public List<Size> getSizes() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Size").list();
    }
    
}

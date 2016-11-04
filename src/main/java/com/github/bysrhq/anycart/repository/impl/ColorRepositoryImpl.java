/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.repository.impl;

import com.github.bysrhq.anycart.domain.Color;
import com.github.bysrhq.anycart.repository.ColorRepository;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bysrhq
 */
@Repository
public class ColorRepositoryImpl implements ColorRepository {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Color getColor(String id) {
        return (Color) sessionFactory.getCurrentSession().get(Color.class, id);
    }

    @Override
    public List<Color> getColors() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Color").list();
    }
    
    
}

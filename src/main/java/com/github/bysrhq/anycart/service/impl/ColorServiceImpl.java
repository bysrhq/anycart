/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service.impl;

import com.github.bysrhq.anycart.domain.Color;
import com.github.bysrhq.anycart.repository.ColorRepository;
import com.github.bysrhq.anycart.service.ColorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bysrhq
 */
@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Transactional
    @Override
    public Color getColor(String id) {
        return colorRepository.getColor(id);
    }

    @Transactional
    @Override
    public List<Color> getColors() {
        return colorRepository.getColors();
    }
    
}

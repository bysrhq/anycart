/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service.impl;

import com.github.bysrhq.anycart.domain.Size;
import com.github.bysrhq.anycart.repository.SizeRepository;
import com.github.bysrhq.anycart.service.SizeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bysrhq
 */
@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;
    
    @Transactional
    @Override
    public Size getSize(String id) {
        return sizeRepository.getSize(id);
    }
    
    @Transactional
    @Override
    public List<Size> getSizes() {
        return sizeRepository.getSizes();
    }
    
    
}

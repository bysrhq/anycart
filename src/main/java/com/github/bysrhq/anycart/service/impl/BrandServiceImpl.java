/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service.impl;

import com.github.bysrhq.anycart.domain.Brand;
import com.github.bysrhq.anycart.repository.BrandRepository;
import com.github.bysrhq.anycart.service.BrandService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author bysrhq
 */
@Service
public class BrandServiceImpl implements BrandService {
    
    @Autowired
    private BrandRepository brandRepository;
    
    @Transactional
    @Override
    public Brand getBrand(String id) {
        return brandRepository.getBrand(id);
    }

    @Transactional
    @Override
    public List<Brand> getBrands() {
        return brandRepository.getBrands();
    }
    
    
}

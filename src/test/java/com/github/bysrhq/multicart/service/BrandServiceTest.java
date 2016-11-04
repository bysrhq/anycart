/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.multicart.service;

import com.github.bysrhq.anycart.repository.BrandRepository;
import com.github.bysrhq.anycart.service.BrandService;
import com.github.bysrhq.anycart.service.impl.BrandServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author bysrhq
 */
@RunWith(MockitoJUnitRunner.class)
public class BrandServiceTest {
    @Mock
    private BrandRepository brandRepository;
    
    @Before
    public void setUp() {
        BrandService brandService = new BrandServiceImpl();
        
    }
    @Test
    public void shouldProcessGetBrands() {
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service;

import com.github.bysrhq.anycart.domain.Brand;
import java.util.List;

/**
 *
 * @author bysrhq
 */
public interface BrandService {
    Brand getBrand(String id);
    List<Brand> getBrands();
}

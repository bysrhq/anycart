/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service;

import java.util.List;
import com.github.bysrhq.anycart.domain.Size;

/**
 *
 * @author bysrhq
 */
public interface SizeService {
    Size getSize(String id);
    List<Size> getSizes();
}

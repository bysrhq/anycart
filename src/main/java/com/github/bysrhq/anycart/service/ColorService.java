/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.service;

import com.github.bysrhq.anycart.domain.Color;
import java.util.List;

/**
 *
 * @author bysrhq
 */
public interface ColorService {
    Color getColor(String id);
    List<Color> getColors();
}

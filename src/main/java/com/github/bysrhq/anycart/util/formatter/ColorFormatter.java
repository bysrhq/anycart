/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.util.formatter;

import com.github.bysrhq.anycart.domain.Color;
import com.github.bysrhq.anycart.service.ColorService;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author bysrhq
 */
@Component
public class ColorFormatter implements Formatter<Color> {
    
    @Autowired
    private ColorService colorService;
    
    @Override
    public String print(Color object, Locale locale) {
        
        return object != null ? object.getId() : "";
    }

    @Override
    public Color parse(String text, Locale locale) throws ParseException {
        
        return colorService.getColor(text);
    }
    
}

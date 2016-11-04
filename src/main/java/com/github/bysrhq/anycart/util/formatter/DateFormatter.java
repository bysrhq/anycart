/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.util.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

/**
 *
 * @author bysrhq
 */
public class DateFormatter implements Formatter<Date> {
    
    private MessageSource messageSource;

    public DateFormatter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
   
    @Override
    public String print(Date object, Locale locale) {
        return createDateFormat(locale)
                .format(object);
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return createDateFormat(locale)
                .parse(text);
    }
    
     private SimpleDateFormat createDateFormat(Locale locale) {
        String format = messageSource.getMessage("format.date", null, locale);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        
        return dateFormat;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.config;

import com.github.bysrhq.anycart.domain.Brand;
import com.github.bysrhq.anycart.util.formatter.BrandFormatter;
import com.github.bysrhq.anycart.util.formatter.CategoryFormatter;
import com.github.bysrhq.anycart.util.formatter.ColorFormatter;
import com.github.bysrhq.anycart.util.formatter.DateFormatter;
import com.github.bysrhq.anycart.util.formatter.ItemFormatter;
import com.github.bysrhq.anycart.util.formatter.SizeFormatter;
import com.github.bysrhq.anycart.util.formatter.UserFormatter;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.AjaxThymeleafViewResolver;
import org.thymeleaf.spring4.view.FlowAjaxThymeleafView;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 *
 * @author bysrhq
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.github.bysrhq.anycart.controller")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    
    @Autowired
    private SizeFormatter sizeFormatter;
    @Autowired
    private ColorFormatter colorFormatter;
    @Autowired
    private BrandFormatter brandFormatter;
    @Autowired
    private CategoryFormatter categoryFormatter;
    @Autowired
    private ItemFormatter itemFormatter;
    @Autowired
    private UserFormatter userFormatter;
    
    @Bean
    public TemplateResolver templateResolver() {
        TemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/thymeleaf/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        
        return templateResolver;
    }
    
    @Bean
    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(new SpringSecurityDialect());
        
        return templateEngine;
    }
    
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);

        return viewResolver;
    }
    
    @Bean
    public AjaxThymeleafViewResolver flowViewResolver(SpringTemplateEngine templateEngine) {
        AjaxThymeleafViewResolver viewResolver = new AjaxThymeleafViewResolver();
        viewResolver.setViewClass(FlowAjaxThymeleafView.class);
        viewResolver.setTemplateEngine(templateEngine);
        
        return viewResolver;
    }
    
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5242880);
        multipartResolver.setMaxInMemorySize(0);
        
        return multipartResolver;
    }
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:com/github/bysrhq/anycart/property/i18n/message");
        messageSource.setCacheSeconds(1);
        
        return messageSource;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        localeResolver.setCookieName("localeLanguage");
        localeResolver.setCookieMaxAge(3600);
        
        return localeResolver;
    }
    
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        
        return localeChangeInterceptor;
    }
            
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/WEB-INF/fonts/");
        registry.addResourceHandler("/img/**").addResourceLocations("file:c:/image/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

//    @Bean
//    public FormattingConversionService mvcConversionService() {        
//        FormattingConversionService conversionService = new FormattingConversionService();
//        conversionService.addFormatter(new DateFormatter(messageSource()));
//        conversionService.addFormatter(new SizeFormatter(sizeService));
//        conversionService.addFormatter(new ColorFormatter(colorService));
//        conversionService.addFormatter(new BrandFormatter(brandService));
//        conversionService.addFormatter(new CategoryFormatter(categoryService));
//        conversionService.addFormatter(new UserFormatter(userService));
//        conversionService.addFormatter(new ItemFormatter(itemService));
//        
//        return conversionService;
//    }
    
    @Bean
    public FormattingConversionService mvcConversionService() {        
        FormattingConversionService conversionService = new FormattingConversionService();
        conversionService.addFormatter(new DateFormatter(messageSource()));
        conversionService.addFormatter(sizeFormatter);
        conversionService.addFormatter(colorFormatter);
        conversionService.addFormatter(brandFormatter);
        conversionService.addFormatter(categoryFormatter);
        conversionService.addFormatter(itemFormatter);
        conversionService.addFormatter(userFormatter);
        
        return conversionService;
    }
    
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new DateFormatter(messageSource()));
//        registry.addFormatter(new SizeFormatter(sizeService));
//        registry.addFormatter(new ColorFormatter(colorService));
//        registry.addFormatter(new BrandFormatter(brandService));
//        registry.addFormatter(new CategoryFormatter(categoryService));
//        registry.addFormatter(new UserFormatter(userService));
//        registry.addFormatter(new ItemFormatter(itemService));
//    }
    
}

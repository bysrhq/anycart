/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.bysrhq.anycart.config.flow;

import com.github.bysrhq.anycart.config.WebMvcConfig;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.thymeleaf.spring4.view.AjaxThymeleafViewResolver;

/**
 *
 * @author bysrhq
 */
@Configuration
@Import(WebMvcConfig.class)
public class WebFlowConfig extends AbstractFlowConfiguration {
    @Value("#{flowViewResolver}")
    private AjaxThymeleafViewResolver flowViewResolver;
    @Value("#{mvcConversionService}")
    private FormattingConversionService mvcConversionService;
    
    @Bean
    public MvcViewFactoryCreator mvcViewFactoryCreator() {
        MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
        factoryCreator.setViewResolvers(Arrays.<ViewResolver>asList(flowViewResolver));
        factoryCreator.setUseSpringBeanBinding(true);
        
        return factoryCreator;
    }
    
    @Bean
    public DefaultConversionService defaultConversionService() {
        return new DefaultConversionService(mvcConversionService);
    }
    
    @Bean
    public FlowBuilderServices flowBuilderServices() {
        return getFlowBuilderServicesBuilder()
                .setViewFactoryCreator(mvcViewFactoryCreator())
                .setConversionService(defaultConversionService())
                .setDevelopmentMode(true)
                .build();
    }
    
    @Bean
    public FlowExecutor flowExecutor()  {
        return getFlowExecutorBuilder(flowRegistry())
                .build();
    }
    
    @Bean
    public FlowDefinitionRegistry flowRegistry() {
        return getFlowDefinitionRegistryBuilder(flowBuilderServices())
                .setBasePath("/WEB-INF/flow")
                .addFlowLocationPattern("/**/*-flow.xml")
                .build();
    }
    
    @Bean
    public FlowHandlerMapping flowHandlerMapping() {
        FlowHandlerMapping flowHandlerMapping = new FlowHandlerMapping();
        flowHandlerMapping.setFlowRegistry(flowRegistry());
        return flowHandlerMapping;
    }
    
    @Bean
    public FlowHandlerAdapter flowHandlerAdapter() {
        FlowHandlerAdapter flowHandlerAdapter = new FlowHandlerAdapter();
        flowHandlerAdapter.setFlowExecutor(flowExecutor());
        
        return flowHandlerAdapter;
    }
    
}

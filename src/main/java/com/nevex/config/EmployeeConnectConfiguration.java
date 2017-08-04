package com.nevex.config;

import com.nevex.ws.ViewNames;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by mcunningham on 8/3/2017.
 */
@Configuration
public class EmployeeConnectConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName(ViewNames.LOGIN_PAGE);
        registry.addViewController("/"+ ViewNames.LOGIN_VIEW_NAME).setViewName(ViewNames.LOGIN_PAGE);
        registry.addViewController("/"+ ViewNames.HOME_VIEW_NAME).setViewName(ViewNames.HOME_PAGE);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String resourcePattern = "/resources/**";
        if ( !registry.hasMappingForPattern(resourcePattern)) {
            registry.addResourceHandler(resourcePattern).addResourceLocations("/resources/");
        }
    }

}

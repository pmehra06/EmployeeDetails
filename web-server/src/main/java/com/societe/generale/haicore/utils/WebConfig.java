package com.societe.generale.haicore.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This configuration is meant only for dev use and is not deployed in any deployment configuration.
 */
@EnableWebMvc
@Configuration
@Order(value = 0)
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //enable CORS for using webpack server
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "OPTIONS" , "PATCH");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:./web-client/dist/hai-portal/")
                    .resourceChain(false);

            registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/webjars/", "/webjars/");

//      Configuration for swagger-ui.html endpoint
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}

package com.imooc.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname RestConfig
 * @Description TODO
 * @Date 2020/1/27 22:18
 * @Created by 于明灏
 */
@Configuration
public class RestConfig implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**/")
                .addResourceLocations("file:D:/shop/image/face/")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}

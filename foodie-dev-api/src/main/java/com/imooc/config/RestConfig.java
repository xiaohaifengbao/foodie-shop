package com.imooc.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname RestConfig
 * @Description TODO
 * @Date 2020/1/27 22:18
 * @Created by 于明灏
 */
@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}

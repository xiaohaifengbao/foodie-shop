package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Classname ImageConfig
 * @Description TODO
 * @Date 2020/1/30 9:33
 * @Created by 于明灏
 */
@Configuration
@ConfigurationProperties(prefix = "file")
@PropertySource(value = "classpath:file-upload-dev.properties")
@Data
public class ImageConfig{

    String uploadPath;
    String prefixUrl;
}

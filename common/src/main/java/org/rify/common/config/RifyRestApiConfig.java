package org.rify.common.config;

import org.rify.common.annotation.RestApiController;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : lucas
 * @version 1.0
 * @date : 2023/10/20下午11:40
 * @className : RifyRestApiConfig
 * @description : RestApiController 配置类
 */
@Configuration
public class RifyRestApiConfig implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/api", config -> config.isAnnotationPresent(RestApiController.class));
        System.out.println("--->" + configurer.getPathMatcher());
    }
}

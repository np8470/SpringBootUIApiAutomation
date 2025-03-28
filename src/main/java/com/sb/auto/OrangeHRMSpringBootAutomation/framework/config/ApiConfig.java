package com.sb.auto.OrangeHRMSpringBootAutomation.framework.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApiConfig {

    @Value("${api.base.url}")
    private String baseURL;

    /*public String getBaseUrl() {
        return baseURL;
    }*/

}

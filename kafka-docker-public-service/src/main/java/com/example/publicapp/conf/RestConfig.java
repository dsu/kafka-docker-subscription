package com.example.publicapp.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Value("${service.basic-auth.user}")
    private String user;

    @Value("${service.basic-auth.password}")
    private String password;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        if(StringUtils.isEmpty(user) || StringUtils.isEmpty(password)){
            throw new RuntimeException("Invalid authentication credentials");
        }
        return builder.basicAuthentication(user, password).build();
    }


}

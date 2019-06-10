package com.git.kinomax094.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration()
public class RestTamplateConfig {


    @Bean()
    public RestTemplate create() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}

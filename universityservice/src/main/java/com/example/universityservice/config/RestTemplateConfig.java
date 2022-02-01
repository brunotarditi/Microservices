package com.example.universityservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean("restClient")
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

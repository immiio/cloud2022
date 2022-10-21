package com.uyyu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  //开启restTemplate的负载均衡，确定调用CLOUD-PAYMENT-SERVICE是哪个端口提供服务
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

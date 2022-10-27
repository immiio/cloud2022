package com.uyyu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
//在这里配置我们自定义的LoadBalancer策略 如果想自定义扩展算法 需要实现ReactorServiceInstanceLoadBalancer接口
//@LoadBalancerClients(defaultConfiguration = {name = "CLOUD-PAYMENT-SERVICE", configuration = CustomLoadBalancerConfiguration.class})
@LoadBalancerClient(name = "CLOUD-PAYMENT-SERVICE",configuration = CustomLoadBalancerConfiguration.class)//name属性: 需要和eureka页面中的服务提供者名字一致
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  //开启restTemplate的负载均衡，确定调用CLOUD-PAYMENT-SERVICE是哪个端口提供服务
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

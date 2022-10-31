package com.uyyu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder.routes()
                //route(String id, Function<PredicateSpec, Buildable<Route>> fn)
                .route("path_route3",r -> r.path("/guonei")  //映射：访问localhost:9527/guonei 将会映射到uri中的地址
                        .uri("http://news.baidu.com/guonei"))
                .build();
    }

//    @Bean
//    public RouteLocator routeLocator1(RouteLocatorBuilder routeLocatorBuilder){
//        return routeLocatorBuilder.routes()
//                //route(String id, Function<PredicateSpec, Buildable<Route>> fn)
//                .route("payment_route1",r -> r.path("/payment/get/**")
//                        .uri("http://localhost:8001"))
//                .build();
//    }

}

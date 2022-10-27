package com.uyyu.springcloud.service;

import com.uyyu.springcloud.entities.Payment;
import com.uyyu.springcloud.entities.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("CLOUD-PAYMENT-SERVICE") //value：调用的微服务的名称（注册中心上挂牌的名字）
public interface PaymentFeignService {
    //可以直接去provider中将controller复制过来

    @PostMapping("/payment/create")
    Result create(@RequestBody Payment payment);

    @GetMapping("/payment/get/{id}")
    Result<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/discovery")
    Object discovery();

    @GetMapping("/payment/feign/timeout")
    String paymentFeignTimeout();
}



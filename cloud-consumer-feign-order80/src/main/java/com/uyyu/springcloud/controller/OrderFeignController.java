package com.uyyu.springcloud.controller;

import com.uyyu.springcloud.entities.Payment;
import com.uyyu.springcloud.entities.Result;
import com.uyyu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/create")
    public Result create(Payment payment){
        return paymentFeignService.create(payment);
    }

    @GetMapping("/get/{id}")
    public Result getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("/discovery")
    public Object discovery(){
        return paymentFeignService.discovery();
    }

    @GetMapping("/feign/timeout")
    public String paymentFeignTimeout(){
        return paymentFeignService.paymentFeignTimeout();
    }

}

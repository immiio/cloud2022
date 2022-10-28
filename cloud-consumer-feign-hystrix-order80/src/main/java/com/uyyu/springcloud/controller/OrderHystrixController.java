package com.uyyu.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.uyyu.springcloud.service.PaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "paymentGlobalFallbackMethod")
public class OrderHystrixController {

    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
//    })
    @HystrixCommand    //没有指明fallbackMethod，使用全局fallback
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        int age = 10 / 0;  //模拟自身报错
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    /**
     * 指定的fallback
     */
    public String paymentInfo_TimeOutHandler(@PathVariable("id") Integer id){
        return "消费端80，因支付系统繁忙，请稍后再试, id: " + id + "\to(╥﹏╥)o";
    }

    /**
     * 全局fallback
     */
    public String paymentGlobalFallbackMethod(){
        return "Global异常处理信息，请稍后再试";
    }
}

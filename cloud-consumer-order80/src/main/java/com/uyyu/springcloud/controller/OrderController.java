package com.uyyu.springcloud.controller;

import com.uyyu.springcloud.entities.Payment;
import com.uyyu.springcloud.entities.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_URL = "http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/payment/create") //客户端需要获取调用服务提供者的那端，获取资源发的都是Get请求，服务端已经写好post，客户端只需要postForObject
    public Result create(Payment payment){
        /*
            String url, @Nullable Object request, Class<T> responseType
            url：REST请求地址 (即要调用的服务的url)
            request：请求参数
            responseType：HTTP响应转换被转换成的对象类型
         */
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, Result.class); //调用服务端post请求就是POSTForObject
    }

    @GetMapping("/payment/get/{id}")
    public Result<Payment> getPaymentById(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, Result.class); //调用服务端get请求就是getForObject
    }

}

package com.uyyu.springcloud.controller;

import com.uyyu.springcloud.entities.Payment;
import com.uyyu.springcloud.entities.Result;
import com.uyyu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public Result create(@RequestBody Payment payment){ //@RequestBody：获取请求的请求体，为形参赋值  *仅POST有请求体
        int result = paymentService.create(payment);
        log.info("***插入结果：" + result);

        if(result > 0){
            return new Result(200,"插入数据库成功,serverPort:" + serverPort,result);
        }else {
            return new Result(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/get/{id}")
    public Result getPaymentById(@PathVariable("id") Long id){ //@RequestBody：获取请求的请求体，为形参赋值  *仅POST有请求体
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果：" + payment);

        if(payment != null){
            return new Result(200,"查询成功,serverPort:" + serverPort,payment);
        }else {
            return new Result(444,"查询失败，没有对应记录",null);
        }
    }

    @GetMapping("/discovery")
    public Object discovery(){
        // 获得微服务清单列表
        List<String> services = discoveryClient.getServices();
        for(String element : services){
            log.info("***elements:" + element);
        }

        // 进一步获得获得对外暴露的具体的微服务名称下的相关信息
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance instance : instances){
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }

        return this.discoveryClient;
    }

}

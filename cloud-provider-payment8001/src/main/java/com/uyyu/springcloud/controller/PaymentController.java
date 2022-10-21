package com.uyyu.springcloud.controller;

import com.uyyu.springcloud.entities.Payment;
import com.uyyu.springcloud.entities.Result;
import com.uyyu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Result create(@RequestBody Payment payment){ //@RequestBody：获取请求的请求体，为形参赋值  *仅POST有请求体
        int result = paymentService.create(payment);
        log.info("***插入结果：" + result);

        if(result > 0){
            return new Result(200,"插入数据库成功",result);
        }else {
            return new Result(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/get/{id}")
    public Result getPaymentById(@PathVariable("id") Long id){ //@RequestBody：获取请求的请求体，为形参赋值  *仅POST有请求体
        Payment payment = paymentService.getPaymentById(id);
        log.info("***查询结果：" + payment);

        if(payment != null){
            return new Result(200,"查询成功",payment);
        }else {
            return new Result(444,"查询失败，没有对应记录",null);
        }
    }

}

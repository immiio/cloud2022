package com.uyyu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

//此处仅做测试，就不写service接口了，直接impl
@Service
public class PaymentService {

    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_OK, id: " + id + "\tO(∩_∩)O哈哈~";
    }

    /**
     * 模拟复杂业务逻辑
     * 设置自身调用超时时间的峰值(3s)，峰值内可以正常运行,超过了需要有兜底的方法处理，作服务降级fallback
     * @param id
     * @return
     */
    // 一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000") //设置该线程的超时时间的峰值，超时报错后调用兜底方案
    })
    public String paymentInfo_TimeOut(Integer id){
        int timeNumber = 3;
        //int age = 10 / 0;  //模拟报错
        try {
            TimeUnit.SECONDS.sleep(timeNumber); //模拟超时报错
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + "paymentInfo_TimeOut, id: " + id + "\t耗时(s): " + timeNumber;
    }

    /**
     * 兜底方案
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutHandler(Integer id){
        return "线程池： " + Thread.currentThread().getName() + "系统繁忙，请稍后再试, id: " + id + "\to(╥﹏╥)o";
    }

    //==================服务熔断====================

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")  //失败率达到多少以后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id < 0){
            throw new RuntimeException("*** id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t调用成功， 流水号： " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能为负数，请稍后再试... id: " + id;
    }
}

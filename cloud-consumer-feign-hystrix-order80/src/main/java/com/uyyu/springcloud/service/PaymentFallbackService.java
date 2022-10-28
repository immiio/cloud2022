package com.uyyu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----PaymentFallbackService fallback - paymentInfo_OK,o(╥﹏╥)o----";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----PaymentFallbackService fallback - paymentInfo_TimeOut,o(╥﹏╥)o----";
    }
}

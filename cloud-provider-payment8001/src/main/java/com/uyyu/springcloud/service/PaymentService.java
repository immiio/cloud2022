package com.uyyu.springcloud.service;

import com.uyyu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}

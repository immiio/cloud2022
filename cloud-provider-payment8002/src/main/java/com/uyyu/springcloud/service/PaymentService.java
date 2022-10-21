package com.uyyu.springcloud.service;

import com.uyyu.springcloud.entities.Payment;

public interface PaymentService {
    int create(Payment payment);
    Payment getPaymentById(Long id);
}

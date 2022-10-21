package com.uyyu.springcloud.dao;

import com.uyyu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper //无需在spring配置中设置扫描地址，spring将动态的生成Bean后注入
public interface PaymentDao {
     int create(Payment payment);
     Payment getPaymentById(@Param("id") Long id);  // @Param：为SQL中参数赋值
}

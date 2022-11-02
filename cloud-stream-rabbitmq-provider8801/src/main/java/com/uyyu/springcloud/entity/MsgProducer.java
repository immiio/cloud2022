package com.uyyu.springcloud.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.Supplier;

@Component
@Slf4j
public class MsgProducer {
    private int id = 1;

    /**
     * Supplier: 每秒一个的频率 自动产生消息 (可在yml中修改频率)
     * @return
     */
    @Bean
    public Supplier<Message<?>> msgSource8801() {
        return () -> {
            String serial = UUID.randomUUID().toString();
            log.info("发送第{}条消息: " + serial, id++);
            return MessageBuilder.withPayload(serial).build();
        };
    }
}

package com.uyyu.springcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
@Slf4j
public class MsgConsumer {
    @Bean
    public Consumer<Message<?>> msgSink8802(){
        return message -> log.info("获取消息： " + message);
    }
}

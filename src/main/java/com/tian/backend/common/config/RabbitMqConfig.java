package com.tian.backend.common.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author muyang.tian
 * @date 2021/5/18 18:10
 */
@Configuration
public class RabbitMqConfig {

    @Value("${spring.application.name}")
    private String queue;
    @Value("${spring.application.name}")
    private String exchange;


    @Bean
    public Queue testDirectQueue() {
        return new Queue(queue, true);
    }

    @Bean
    FanoutExchange testDirectExchange() {
        return new FanoutExchange(exchange);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting

    /**
     * @return 绑定
     */
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange());
    }
}

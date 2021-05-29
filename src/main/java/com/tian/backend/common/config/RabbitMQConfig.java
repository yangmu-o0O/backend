package com.tian.backend.common.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author muyang.tian
 * @date 2021/5/18 18:10
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue TestDirectQueue(){
        return new Queue("testDirectQueue",true);
    }

    @Bean
    DirectExchange TestDirectExchange(){
        return new DirectExchange("testDirectExchange",true,false);
    }

    //绑定  将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("testDirectRouting");
    }
}

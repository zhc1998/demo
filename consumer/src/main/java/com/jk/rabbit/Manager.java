package com.jk.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class Manager {
    @Bean//把当前队列注入到spring环境中
    public Queue AddOrder(){
        return new Queue("AddOrder");
        
    }

    @Bean//把当前队列注入到spring环境中
    public Queue DelOrder(){
        return new Queue("DelOrder");

    }

    @Bean//把当前队列注入到spring环境中
    public Queue addredistOrder(){
        return new Queue("addredistOrder");

    }

    @Bean//把当前队列注入到spring环境中
    public Queue seckill(){
        return new Queue("seckill");

    }
}

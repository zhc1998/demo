package com.jk.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @auther TyCoding
 * @date 2018/10/8
 */
@Configuration
public class JedisConfig {
    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);

    @Value("localhost")
    private String host;

    @Value("6379")
    private int port;

    @Value("1000")
    private int timeout;

    @Value("1000")
    private int maxActive;

    @Value("20")
    private int maxIdle;

    @Value("10")
    private int minIdle;

    @Value("10")
    private long maxWaitMillis;

    @Bean
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, null);

        logger.info("JedisPool注入成功");
        logger.info("redis地址：" + host + ":" + port);
        return jedisPool;
    }
}

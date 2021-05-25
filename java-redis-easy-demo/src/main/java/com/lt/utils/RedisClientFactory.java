package com.lt.utils;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author liangtao
 * @description
 * @date 2021年05月06 15:58
 **/
public class RedisClientFactory {
//    private static final String HOST = "121.199.44.208";
//private static final int PORT = 6379;
//    private static final String PWD = "redis123";
    private static final String HOST = "202.119.11.121";
    private static final int PORT = 6388;
    private static final String PWD = "Agile@123456";

    private static RedisClient REDIS_CLIENT;

    static {
        // <1> 创建单机连接的连接信息
        RedisURI redisUri = RedisURI.builder()
                .withHost(HOST)
                .withPort(PORT)
//                .withPassword(PWD)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        // <2> 创建客户端
        REDIS_CLIENT = RedisClient.create(redisUri);
    }

    public static RedisClient buildClient() {
        return REDIS_CLIENT;
    }

    public static RedisCommands<String, String> buildCommand() {
        return REDIS_CLIENT.connect().sync();
    }
}

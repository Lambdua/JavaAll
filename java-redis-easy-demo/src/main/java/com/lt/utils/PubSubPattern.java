package com.lt.utils;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

/**
 * @author liangtao
 * @description 发布订阅模式Redis实现
 * @date 2021年05月08 10:05
 **/
public class PubSubPattern {

    static String channel = "channel1";

    public void demo() throws ExecutionException, InterruptedException {
        //订阅者线程
        Thread subThread = new Thread(() -> {
            RedisClient clent = RedisClientFactory.buildClient();
            StatefulRedisPubSubConnection<String, String> psConnection = clent.connectPubSub();
            RedisPubSubAsyncCommands<String, String> psCommand = psConnection.async();
            psCommand.subscribe(channel);
            psConnection.addListener(new RedisPubSubAdapter<String, String>() {
                @Override
                public void message(String channel, String message) {
                    System.out.println("channel = " + channel);
                    System.out.println("message = " + message);
                }
            });
        });
        subThread.run();
        //发布者
        RedisClient clent = RedisClientFactory.buildClient();
        StatefulRedisPubSubConnection<String, String> psConnection = clent.connectPubSub();
        RedisPubSubAsyncCommands<String, String> psCommand = psConnection.async();
        while (true) {
            psCommand.publish(channel, "发布的消息" + LocalDateTime.now()).get();
            //没一秒发送一次
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        PubSubPattern entity = new PubSubPattern();
        entity.demo();
    }

}

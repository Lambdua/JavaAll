package com.lt.behavioral.templatePattern.instance2.networks;

import lombok.SneakyThrows;

/**
 * @author liangtao
 * @description 网络处理抽象类
 * @date 2021年03月31 10:51
 **/
public abstract class Network {
    String username;
    String pwd;

    @SneakyThrows
    public boolean post(String msg){
        if (logIn(username,pwd)){
            boolean result = sendData(msg.getBytes("UTF-8"));
            logOut();
            return result;
        }
        return false;

    }


    abstract boolean logIn(String userName, String password);
    abstract boolean sendData(byte[] data);
    abstract void logOut();
}

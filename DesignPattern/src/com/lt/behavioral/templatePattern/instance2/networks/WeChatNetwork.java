package com.lt.behavioral.templatePattern.instance2.networks;

import lombok.SneakyThrows;

/**
 * @author liangtao
 * @description 微信网络
 * @date 2021年03月31 10:54
 **/
public class WeChatNetwork extends Network {

    public WeChatNetwork(String username,String pwd) {
        this.username=username;
        this.pwd=pwd;
    }

    @SneakyThrows
    @Override
    boolean logIn(String userName, String password) {
        System.out.println("进行微信登录");
        System.out.println("用户名： "+userName+",密码： "+password);
        for (int i = 0; i < 3; i++) {
            System.out.print(".");
            Thread.sleep(300);
        }
        System.out.println("登录成功");
        return true;
    }

    @Override
    boolean sendData(byte[] data) {
        System.out.println("微信发送数据: "+new String(data));
        return true;
    }

    @SneakyThrows
    @Override
    void logOut() {
        System.out.println("退出微信登录..");
        Thread.sleep(300);
    }
}

package com.lt.behavioral.templatePattern.instance2.networks;

import lombok.SneakyThrows;

/**
 * @author liangtao
 * @description 微博网络通讯
 * @date 2021年03月31 11:12
 **/
public class WeiBoNetwork extends Network{
    public WeiBoNetwork(String username,String pwd) {
        this.username=username;
        this.pwd=pwd;
    }


    @Override
    @SneakyThrows
    boolean logIn(String userName, String password) {
        System.out.println("进行微博登录");
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
        System.out.println("微博发送数据: "+new String(data));
        return true;
    }

    @Override
    @SneakyThrows
    void logOut() {
        System.out.println("退出微博登录..");
        Thread.sleep(300);

    }
}

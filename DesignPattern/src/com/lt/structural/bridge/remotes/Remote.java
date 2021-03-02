package com.lt.structural.bridge.remotes;

/**
 * @author liangtao
 * @description 所有远程控制器的通用接口
 * @date 2021年03月02 09:51
 **/
public interface Remote {
    void power();

    void volumeDown();

    void volumeUp();

    void channelDown();

    void channelUp();
}

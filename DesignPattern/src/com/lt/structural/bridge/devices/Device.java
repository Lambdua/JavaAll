package com.lt.structural.bridge.devices;

/**
 * @author liangtao
 * @description 所有设备的通用接口
 * @date 2021年03月02 09:44
 **/
public interface Device {
    boolean isEnabled();

    void enabled();

    void disabled();

    //音量
    int getVolume();

    void setVolume(int percent);

    //频道
    int getChannel();

    void setChannel(int channel);

    void printStatus();
}

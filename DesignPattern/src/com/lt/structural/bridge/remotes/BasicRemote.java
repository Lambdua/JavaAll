package com.lt.structural.bridge.remotes;

import com.lt.structural.bridge.devices.Device;

/**
 * @author liangtao
 * @description 基础远程控制器
 * @date 2021年03月02 09:52
 **/
public class BasicRemote implements Remote {
    protected Device device;

    public BasicRemote(Device device) {
        this.device = device;
    }

    @Override
    public void power() {
        System.out.println("遥控器电源按钮触发");
        if (device.isEnabled()) {
            device.disabled();
        } else device.enabled();

    }

    @Override
    public void volumeDown() {
        System.out.println("遥控器： 音量下调");
        device.setVolume(device.getVolume() - 10);
    }

    @Override
    public void volumeUp() {
        System.out.println("遥控器： 音量增加");
        device.setVolume(device.getVolume() + 10);
    }

    @Override
    public void channelDown() {
        System.out.println("遥控器： 频道切换下一个");
        device.setChannel(device.getChannel() - 1);
    }

    @Override
    public void channelUp() {
        System.out.println("遥控器： 频道切换上一个");
        device.setChannel(device.getChannel() + 1);
    }
}

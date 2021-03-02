package com.lt.structural.bridge.devices;

/**
 * @author liangtao
 * @description 设备实现类-->收音机
 * @date 2021年03月02 09:46
 **/
public class Redio implements Device {
    private boolean on = false;
    private int volume = 30;
    private int channel = 1;

    @Override
    public boolean isEnabled() {
        return on;
    }

    @Override
    public void enabled() {
        this.on = true;
    }

    @Override
    public void disabled() {
        this.on = false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int percent) {
        if (percent >= 100) this.volume = 100;
        else if (percent <= 0) this.volume = 0;
        else this.volume = percent;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        if (channel < 1) this.channel = 50;
        else if (channel >= 50) this.channel = 1;
        else this.channel = channel;
    }

    @Override
    public void printStatus() {
        System.out.println("------------------------------------");
        System.out.println("| 我是收音机.");
        System.out.println("| 我现在 " + (on ? "开机的" : "关机的"));
        System.out.println("| 当前音量:  " + volume + "%");
        System.out.println("| 当前频道是: " + channel);
        System.out.println("------------------------------------\n");
    }
}

package com.lt.structural.bridge.remotes;

import com.lt.structural.bridge.devices.Device;

/**
 * @author liangtao
 * @description 高级远程控制器
 * @date 2021年03月02 10:18
 **/
public class AdvancedRemote extends BasicRemote {
    public AdvancedRemote(Device device) {
        super(device);
    }

    public void menu() {
        System.out.println("高级遥控器： 设备信息菜单");
        device.printStatus();
    }

    public void mute() {
        System.out.println("高级遥控器： 恢复默认");
        device.setVolume(0);
    }
}

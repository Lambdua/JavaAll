package com.lt.structural.bridge;

import com.lt.structural.bridge.devices.Device;
import com.lt.structural.bridge.devices.Redio;
import com.lt.structural.bridge.devices.Tv;
import com.lt.structural.bridge.remotes.AdvancedRemote;
import com.lt.structural.bridge.remotes.BasicRemote;

/**
 * @author liangtao
 * @description 客户端代码
 * @date 2021年03月02 10:20
 **/
public class ClientDemo {
    public static void main(String[] args) {
        testDevice(new Tv());
        testDevice(new Redio());
    }

    public static void testDevice(Device device) {
        System.out.println("开始测试基础远程遥控器");
        BasicRemote basicRemote = new BasicRemote(device);
        basicRemote.power();
        basicRemote.channelDown();
        basicRemote.volumeUp();

        device.printStatus();

        System.out.println("开始测试高级远程遥控器");
        AdvancedRemote advancedRemote = new AdvancedRemote(device);
        advancedRemote.mute();
        advancedRemote.menu();
    }
}

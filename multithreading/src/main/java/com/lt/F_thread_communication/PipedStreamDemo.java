package com.lt.F_thread_communication;

import cn.hutool.core.util.RandomUtil;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;

/**
 * @author 梁先生
 * 管道通信
 * @Date 2020/10/6
 **/
public class PipedStreamDemo {
    private static PipedInputStream input = new PipedInputStream();
    private static PipedOutputStream output = new PipedOutputStream();

    private static void send() {
        new Thread(() -> {
            byte[] bytes;
            while (true) {
                bytes = RandomUtil.randomBytes(1024);
                try {
                    output.write(bytes);
                    System.out.println("发送成功");
                } catch (IOException e) {
                    System.out.println("发送失败");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void receive() {
        new Thread(() -> {
            byte[] bytes = new byte[1024];
            while (true) {
                try {
                    input.read(bytes);
                    System.out.println(Arrays.toString(bytes));
                    System.out.println("接受成功");
                } catch (IOException e) {
                    System.out.println("接受失败");
                    e.printStackTrace();
                }
                System.out.println();
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        input.connect(output);
        send();
        receive();
    }
}

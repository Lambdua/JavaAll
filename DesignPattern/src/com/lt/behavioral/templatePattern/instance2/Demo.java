package com.lt.behavioral.templatePattern.instance2;

import com.lt.behavioral.templatePattern.instance2.networks.Network;
import com.lt.behavioral.templatePattern.instance2.networks.WeChatNetwork;
import com.lt.behavioral.templatePattern.instance2.networks.WeiBoNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author liangtao
 * @description 客户端demo
 * @date 2021年03月31 11:14
 **/
public class Demo {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Network network = null;
        System.out.print("输入用户名：");
        String userName = reader.readLine();
        System.out.print("输入密码：");
        String password = reader.readLine();

        // Enter the message.
        System.out.print("输入信息：");
        String message = reader.readLine();

        System.out.println("\n选择要发布消息的社交网络。\n" +
                "1 - 微信\n" +
                "2 - 微博");
        int choice = Integer.parseInt(reader.readLine());

        // Create proper network object and send the message.
        if (choice == 1) {
            network = new WeChatNetwork(userName, password);
        } else if (choice == 2) {
            network = new WeiBoNetwork(userName, password);
        }
        network.post(message);
    }
}

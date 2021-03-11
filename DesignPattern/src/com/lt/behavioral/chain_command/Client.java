package com.lt.behavioral.chain_command;

import com.lt.behavioral.chain_command.middleware.Middleware;
import com.lt.behavioral.chain_command.middleware.RoleCheckMiddleware;
import com.lt.behavioral.chain_command.middleware.ThrottlingMiddleware;
import com.lt.behavioral.chain_command.middleware.UserExistsMiddleware;
import com.lt.behavioral.chain_command.server.Server;

import java.util.Scanner;

/**
 * @author liangtao
 * @description 客户端
 * @date 2021年03月11 13:53
 **/
public class Client {
    private static Scanner scanner = new Scanner(System.in);
    private static Server server;

    public static void main(String[] args) {
        init();
        boolean success;
        do {
            System.out.print("输入电子邮件：");
            String email = scanner.nextLine();
            System.out.print("输入密码：");
            String password = scanner.nextLine();
            success = server.logIn(email, password);
        } while (true);
    }

    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");
        server.register("temp", "temp");

        // 所有职责链已链接。客户可以使用相同的资源建立各种链
        Middleware middleware = new ThrottlingMiddleware(2);
        middleware.linkWith(new UserExistsMiddleware(server))
                .linkWith(new RoleCheckMiddleware());

        // 服务器从客户端代码获取链。
        server.setMiddleware(middleware);
    }
}

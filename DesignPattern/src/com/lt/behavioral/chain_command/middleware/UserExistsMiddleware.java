package com.lt.behavioral.chain_command.middleware;

import com.lt.behavioral.chain_command.server.Server;

/**
 * @author liangtao
 * @description 检查用户登录信息
 * @date 2021年03月11 13:49
 **/
public class UserExistsMiddleware extends Middleware {
    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    @Override
    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("该电子邮件未注册！");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("密码错误！");
            return false;
        }
        return checkNext(email, password);
    }
}

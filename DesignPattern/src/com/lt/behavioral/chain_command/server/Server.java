package com.lt.behavioral.chain_command.server;

import com.lt.behavioral.chain_command.middleware.Middleware;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @description 授权目标
 * @date 2021年03月11 13:49
 **/
public class Server {
    private Map<String, String> users = new HashMap<>();
    private Middleware middleware;

    /**
     * 客户端将对象链传递给服务器。这提高了灵活性，并使测试服务器类更加容易。
     */
    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    /**
     * 服务器从客户端获取电子邮件和密码，然后将授权请求发送到链。
     */
    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) {
            System.out.println("授权成功！");

            //在此为授权用户做一些有用的事情。

            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}

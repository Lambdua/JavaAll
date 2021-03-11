package com.lt.behavioral.chain_command.middleware;

/**
 * @author liangtao
 * @description 检查用户角色
 * @date 2021年03月11 13:52
 **/
public class RoleCheckMiddleware extends Middleware {

    @Override
    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin!");
            return true;
        }
        System.out.println("Hello, user!");
        return checkNext(email, password);
    }
}

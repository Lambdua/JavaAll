package com.lt.behavioral.chain_command.middleware;

/**
 * @author liangtao
 * @description 基础验证接口
 * @date 2021年03月11 13:43
 **/
public abstract class Middleware {
    private Middleware next;

    public Middleware linkWith(Middleware next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(String email, String password);


    /**
     * 在链中的下一个对象上运行检查，或者如果我们在链中的最后一个对象中，则结束遍历。
     */
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}

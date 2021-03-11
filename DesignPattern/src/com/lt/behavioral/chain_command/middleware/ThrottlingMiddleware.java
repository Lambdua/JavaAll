package com.lt.behavioral.chain_command.middleware;

import lombok.SneakyThrows;

/**
 * @author liangtao
 * @description 检查请求数量限制
 * @date 2021年03月11 13:45
 **/
public class ThrottlingMiddleware extends Middleware {
    private int requestPerMinute;
    private int request;
    private long currentTime;

    public ThrottlingMiddleware(int requestPerMinute) {
        this.requestPerMinute = requestPerMinute;
        this.currentTime = System.currentTimeMillis();
    }

    /**
     * 请不要在此方法的开头和结尾都插入checkNext（）调用。
     * 与对所有中间件对象进行简单循环相比，这提供了更大的灵活性。
     * 例如，链中的元素可以通过在所有其他检查之后运行其检查来更改检查顺序。
     */
    @SneakyThrows
    @Override
    public boolean check(String email, String password) {
        if (System.currentTimeMillis() > currentTime + 60_000) {
            request = 0;
            currentTime = System.currentTimeMillis();
        }

        request++;
        if (request > requestPerMinute) {
            System.out.println("超出了请求限制！");
            System.out.println("等待五秒");
            Thread.sleep(5_000);
        }
        return checkNext(email, password);
    }
}

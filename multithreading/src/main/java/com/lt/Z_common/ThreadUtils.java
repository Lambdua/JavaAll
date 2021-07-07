package com.lt.Z_common;

/**
 * @author liangtao
 * @description
 * @date 2021年07月06 14:46
 **/
public class ThreadUtils {
    public static void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.lt.util;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author liangtao
 * @description 数据接收工具类
 * @date 2021年02月22 11:11
 **/
public class DataIn {
    Scanner scanner;

    public DataIn(String sourceName) {
        this.scanner = new Scanner(getClass().getClassLoader().getResourceAsStream(sourceName), StandardCharsets.UTF_8.name());
    }

    public DataIn() {
        this("tinyTale.txt");
    }

    public boolean isEmpty(){
        return !scanner.hasNext();
    }

    public String readString(){
        return scanner.next();
    }
}

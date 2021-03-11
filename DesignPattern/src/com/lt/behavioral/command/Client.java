package com.lt.behavioral.command;

import com.lt.behavioral.command.edit.Editor;

/**
 * @author liangtao
 * @description 客户端
 * @date 2021年03月11 14:51
 **/
public class Client {
    public static void main(String[] args) {
        Editor editor=new Editor();
        editor.init();
    }
}

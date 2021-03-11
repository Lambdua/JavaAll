package com.lt.behavioral.command.abs_cmd;

import java.util.Stack;

/**
 * @author liangtao
 * @description 命令历史
 * @date 2021年03月11 14:42
 **/
public class CommandHistory {
    private Stack<Command> history=new Stack<>();

    public void push(Command cmd){
        history.push(cmd);
    }

    public Command pop(){
        return history.pop();
    }

    public boolean isEmpty(){
        return history.isEmpty();
    }
}

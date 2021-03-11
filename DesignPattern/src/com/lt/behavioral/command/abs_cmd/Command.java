package com.lt.behavioral.command.abs_cmd;

import com.lt.behavioral.command.edit.Editor;

/**
 * @author liangtao
 * @description 抽象基本命令
 * @date 2021年03月11 14:40
 **/
public abstract class Command {
    public Editor editor;
    private String backup;

    Command(Editor editor) {
        this.editor = editor;
    }

    void backup() {
        backup = editor.textField.getText();
    }

    public abstract boolean execute();

    public void undo() {
        editor.textField.setText(backup);
    }
}

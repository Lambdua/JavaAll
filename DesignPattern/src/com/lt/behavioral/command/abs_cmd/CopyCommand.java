package com.lt.behavioral.command.abs_cmd;

import com.lt.behavioral.command.edit.Editor;

/**
 * @author liangtao
 * @description 将所选文字复制到剪贴板
 * @date 2021年03月11 14:47
 **/
public class CopyCommand extends Command {
    public CopyCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.clipboard = editor.textField.getSelectedText();
        return false;
    }
}

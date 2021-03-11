package com.lt.behavioral.command.abs_cmd;

import com.lt.behavioral.command.edit.Editor;

/**
 * @author liangtao
 * @description 粘贴文字
 * @date 2021年03月11 14:48
 **/
public class PasteCommand extends Command{
    public PasteCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.textField.insert(editor.clipboard,editor.textField.getCaretPosition());
        return true;
    }
}

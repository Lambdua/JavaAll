package com.lt;

import lombok.Data;

/**
 * @author liangtao
 * @Date 2020/7/21
 * 构造socket任务内容，下面为标准的输入和输出任务信息的格式。接收数据后，也用这个格式解析数据。
 **/
@Data
public class SocketProperties {
    private Integer status;

    /**
     * WebCAD：应用名，自己在webcadcommandmanager.exe中配置。
     */
    private String host = "WebCAD";
    private String input;
    private String output;
}

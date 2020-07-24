package com.lt.winHaoChenCad;

import lombok.Data;

/**
 * @author liangtao
 * @Date 2020/7/21
 * 构造socket任务内容，下面为标准的输入和输出任务信息的格式。接收数据后，也用这个格式解析数据。
 **/
@Data
public class SocketProperties {
    /**
     * 任务状态
     */
    private Integer status;
    /**
     * WebCAD：应用名，自己在webcadcommandmanager.exe/webcadcfg.xml中配置。
     */
    private String host = "WebCAD";
    /**
     * 要处理的任务的字符串，字符串可以用json分解
     * @see CADProperties
     */
    private String input;
    private String output;
    /**
     * 任务超时毫秒数(不同任务超时数允许不同，但和系统配置的超时数同时起作用)[可选]
     */
    private int timeout=3000;
}

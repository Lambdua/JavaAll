package com.lt.winHaoChenCad;

import lombok.Data;

/**
 * @author liangtao
 * @Date 2020/7/21
 **/
@Data
public class CADProperties {

    /**
     * 任务编号，自定义
     */
    private Integer id;
    /**
     * 图纸名，不支持中文。(测试中文可以)
     */
    private String name;

    /**
     * 图纸目录名称，不能写绝对路径，不支持中文。比如在WebCADManager.exe里面设置的文件位置是C:\test,这里path赋值为1001，
     * 那么test.dwg图纸应在C:\test\dwg\1001文件夹里面，其中dwg文件夹是强制规定的，必须有。
     */
    private String path;

    /**
     * 生成的ocf文件名。依据上面path的例子
     */
    private String ocf;

    /**
     * 布局名称 为空时会自动转换默认图纸空间，
     * 当在前端点击布局功能，切换布局时，会发送请求，里面有layout名称，后台获取后赋值给这个layout。
     */
    private String layout;

    /**
     * 为空即可(用户令牌)
     */
    private String utoken;

    /**
     * 执行转化任务的名称
     * 转ocf:必须是 MakeOcf
     * 转thumb:是 GetThumb
     */
    private String taskclass="MakeOcf";



}

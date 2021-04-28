package com.lt.structural.composite.component;

/**
 * @author liangtao
 * @description 文件组件接口
 * @date 2021年04月06 09:58
 **/
public interface AbstractFile {

    /**
     * 新增
     *
     * @param file
     * @author liangtao
     * @date 2021/4/6
     **/
    void add(AbstractFile file);


    /**
     * 删除
     *
     * @param file
     * @author liangtao
     * @date 2021/4/6
     **/
    void remove(AbstractFile file);

    /**
     * 获取子组件
     *
     * @return com.lt.structural.composite.component.AbstractFile
     * @author liangtao
     * @date 2021/4/6
     **/
    AbstractFile getChild(int i);

    /**
     * 杀毒
     *
     * @author liangtao
     * @date 2021/4/6
     **/
    void killVirus();
}

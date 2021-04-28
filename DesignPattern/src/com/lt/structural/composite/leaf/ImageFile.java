package com.lt.structural.composite.leaf;

import com.lt.structural.composite.component.AbstractFile;
import lombok.AllArgsConstructor;

/**
 * @author liangtao
 * @description 图片文件
 * @date 2021年04月06 10:00
 **/
@AllArgsConstructor
public class ImageFile implements AbstractFile {

    private String name;

    @Override
    public void add(AbstractFile file) {
        System.out.println("对不起，imageFile 叶节点不支持该方法");
    }

    @Override
    public void remove(AbstractFile file) {
        System.out.println("对不起，imageFile 叶节点不支持该方法");
    }

    @Override
    public AbstractFile getChild(int i) {
        System.out.println("对不起，imageFile 叶节点不支持该方法");
        return null;
    }

    @Override
    public void killVirus() {
        System.out.println("模拟杀毒工作，对图片文件"+name+"进行杀毒");
    }
}

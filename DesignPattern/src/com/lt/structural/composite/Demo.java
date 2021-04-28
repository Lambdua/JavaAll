package com.lt.structural.composite;

import com.lt.structural.composite.component.AbstractFile;
import com.lt.structural.composite.container.Folder;
import com.lt.structural.composite.leaf.ImageFile;
import com.lt.structural.composite.leaf.TextFile;
import com.lt.structural.composite.leaf.VideoFile;

/**
 * @author liangtao
 * @description
 * @date 2021年04月28 17:13
 **/
public class Demo {
    public static void main(String[] args) {
        AbstractFile folder1 = new Folder("Sunny的资料");
        AbstractFile folder2 = new Folder("图像文件");
        AbstractFile folder3 = new Folder("文本文件");
        AbstractFile folder4 = new Folder("视频文件");

        AbstractFile file1 = new ImageFile("小龙女.jpg");
        AbstractFile file2 = new ImageFile("张无忌.gif");
        AbstractFile file3 = new TextFile("九阴真经.txt");
        AbstractFile file4 = new TextFile("葵花宝典.doc");
        AbstractFile file5 = new VideoFile("笑傲江湖.rmvb");

        folder2.add(file1);
        folder2.add(file2);
        folder3.add(file3);
        folder3.add(file4);
        folder4.add(file5);
        folder1.add(folder2);
        folder1.add(folder3);
        folder1.add(folder4);

        //进行容器遍历杀毒
        folder1.killVirus();
    }
}

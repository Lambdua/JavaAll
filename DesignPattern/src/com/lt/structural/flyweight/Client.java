package com.lt.structural.flyweight;

import cn.hutool.core.util.RandomUtil;
import com.lt.structural.flyweight.forest.Forest;

import java.awt.*;

/**
 * @author liangtao
 * @description 客户端
 * @date 2021年03月10 08:59
 **/
public class Client {
    /**
     * 画布尺寸
     */
    static int CANVAS_SIZE = 500;

    /**
     * 树的数量
     */
    static int TREES_TO_DRAW = 5000000;

    /**
     * 树类别
     */
    static int TREE_TYPES = 2;


    public static void main(String[] args) {
        Forest forest = new Forest();
        for (int i = 0; i < Math.floor(TREES_TO_DRAW / TREE_TYPES); i++) {
            forest.plantTree(
                    RandomUtil.randomInt(0, CANVAS_SIZE),
                    RandomUtil.randomInt(0, CANVAS_SIZE),
                    "夏季橡树", Color.GREEN, "橡木纹理"
            );
            forest.plantTree(
                    RandomUtil.randomInt(0, CANVAS_SIZE),
                    RandomUtil.randomInt(0, CANVAS_SIZE),
                    "棕树", Color.GRAY, "棕树果"
            );


        }
        forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
        forest.setVisible(true);
        System.out.println(TREES_TO_DRAW + " trees drawn");
        System.out.println("---------------------");
        System.out.println("Memory usage:");
        System.out.println("Tree size (8 bytes) * " + TREES_TO_DRAW);
        System.out.println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "");
        System.out.println("---------------------");
        System.out.println("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
                "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");

    }
}

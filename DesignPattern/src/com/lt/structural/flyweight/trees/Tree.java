package com.lt.structural.flyweight.trees;

import lombok.AllArgsConstructor;

import java.awt.*;

/**
 * @author liangtao
 * @description 包含每棵树独特的状态
 * @date 2021年03月10 08:47
 **/
@AllArgsConstructor
public class Tree {
    private int x;
    private int y;
    private TreeType type;

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }
}

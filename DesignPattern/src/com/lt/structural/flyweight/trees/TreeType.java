package com.lt.structural.flyweight.trees;

import lombok.AllArgsConstructor;

import java.awt.*;

/**
 * @author liangtao
 * @description 包含多颗树共享的状态
 * @date 2021年03月10 08:47
 **/
@AllArgsConstructor
public class TreeType {
    private String name;
    private Color color;
    private String otherTreeData;

    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 1, y, 3, 5);
        g.setColor(color);
        g.fillOval(x - 5, y - 10, 10, 10);
    }

}

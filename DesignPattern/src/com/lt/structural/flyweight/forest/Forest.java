package com.lt.structural.flyweight.forest;

import com.lt.structural.flyweight.trees.Tree;
import com.lt.structural.flyweight.trees.TreeFactory;
import com.lt.structural.flyweight.trees.TreeType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liangtao
 * @description 需要绘制的森林
 * @date 2021年03月10 08:55
 **/
public class Forest extends JFrame {
    private List<Tree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color, String otherTreeData) {
        TreeType type = TreeFactory.getTreeType(name, color, otherTreeData);
        Tree tree = new Tree(x, y, type);
        trees.add(tree);
    }


    @Override
    public void paint(Graphics graphics) {
        for (Tree tree : trees) {
            tree.draw(graphics);
        }
    }
}

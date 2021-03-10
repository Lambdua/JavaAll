package com.lt.structural.flyweight.trees;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @description 工厂类，封装创建享元的复杂机制
 * @date 2021年03月10 08:51
 **/
public class TreeFactory {
    static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, Color color, String otherTreeData) {
        if (treeTypes.containsKey(name)) {
            return treeTypes.get(name);
        }
        TreeType result = new TreeType(name, color, otherTreeData);
        treeTypes.put(name, result);
        return result;
    }
}

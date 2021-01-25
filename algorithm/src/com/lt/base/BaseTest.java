package com.lt.base;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author liangtao
 * @description
 * @date 2021年01月25 16:41
 **/
public class BaseTest {
    public static void main(String[] args) {
        stackTest();
    }

    public static void stackTest() {
        // 创建一个栈并根据StdIn中的指示压入或弹出字符串
        Stack<String> s = new StackByArray<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("show")) {
                for (String st : s) {
                    System.out.print(st + " ");
                }
                System.out.println();

            } else if (!item.equals("-"))
                s.push(item);
            else if (!s.isEmpty()) StdOut.print(s.pop() + " ");
        }
        StdOut.println("(" + s.size() + " left on stack)");
    }
}

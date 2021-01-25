package com.lt.base;

import com.lt.base.queue.Queue;
import com.lt.base.queue.QueueByLink;
import com.lt.base.stack.Stack;
import com.lt.base.stack.StackByArray;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author liangtao
 * @description
 * @date 2021年01月25 16:41
 **/
public class BaseTest {
    public static void main(String[] args) {
//        stackTest();
        queueTest();
    }

    public static void queueTest() {
        // 创建一个队列并操作字符串入列或出列
        Queue<String> q = new QueueByLink<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (item.equals("show")) {
                for (String s : q) {
                    System.out.print(s + " ");
                }
                System.out.println();
            } else if (!item.equals("-"))
                q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("(" + q.size() + " left on queue)");

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

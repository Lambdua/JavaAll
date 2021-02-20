package com.lt.test;


import com.lt.base.pqueue.MaxPQ;
import com.lt.base.queue.Queue;
import com.lt.base.queue.QueueByLink;
import com.lt.base.stack.Stack;
import com.lt.base.stack.StackByArray;
import com.lt.base.symbol.ST;
import com.lt.base.symbol.SequentialSearchST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.IOException;

/**
 * @author liangtao
 * @description
 * @date 2021年01月25 16:41
 **/
public class BaseTest {
    public static void main(String[] args) throws IOException {
//        stackTest();
//        queueTest();
//        maxPQTest();
//        stBehaviorTest();
//        stPerformanceTest(12);
//        stBehaviorTest("tinyTale.txt",new SequentialSearchST<>());
        stPerformanceTest(1, "tinyTale.txt", new SequentialSearchST<>());
    }

    /**
     * 队列测试
     */
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

    /**
     * 栈测试
     */
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

    /**
     * 优先队列测试
     */
    public static void maxPQTest() {
        MaxPQ<Integer> pq = new MaxPQ<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(Integer.valueOf(item));
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }

    /**
     * 符号表的行为测试用例
     * 这段代码会从标准输入接受多个字符串，构造一张符号表来将 i 和第 i 个字符串相关联，然后打印
     * 符号表。我们假设所有的字符串都只有一个字母。一般我们会使用 "S E A R C H E X A  M P L E"。
     * 按照我们的约定，用例会将键 S 和 0，键 R 和 3 关联起来，等等。但对于重复的字母，如E则是和12 、A则是和8关联起来。
     * 即<String,Integet>的<k,v>形式，重复元素的put会被顶替掉
     *
     * @param sourceName 测试数据source目录下的名称
     */
    public static void stBehaviorTest(String sourceName, ST<String, Integer> st) throws IOException {
        initialSystemIn(sourceName);
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        //对于有序的符号表实现，元素的输出顺序是确定的,无序的符号表输出则是不确定的
        for (String s : st.keys()) StdOut.print(s + "->" + st.get(s) + " ");
    }

    /**
     * 符号表的性能测试用例,用例会从标准输入中得到的一列字符串并记录每个（长度至少达到指定的
     * 阈值）字符串的出现次数，然后遍历所有键并找出出现频率最高的键。
     *
     * @param minLen     筛选的单词最小长度
     * @param sourceName 测试数据source目录下的名称
     */
    public static void stPerformanceTest(int minLen, String sourceName, ST<String, Integer> st) throws IOException {
        initialSystemIn(sourceName);
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            if (word.length() < minLen) continue;
            if (st.contains(word)) st.put(word, st.get(word) + 1);
            else st.put(word, 1);
        }

        String maxKey = " ";
        st.put(maxKey, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(maxKey)) {
                maxKey = word;
            }
        }
        StdOut.println(maxKey + "-->" + st.get(maxKey));
    }

    private static void initialSystemIn(String sourceName) throws IOException {
        System.in.close();
        //初始化system.in
        System.setIn(BaseTest.class.getClassLoader().getResourceAsStream(sourceName));
    }
}

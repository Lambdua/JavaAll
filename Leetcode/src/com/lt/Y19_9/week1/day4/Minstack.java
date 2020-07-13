package com.lt.Y19_9.week1.day4;

import java.util.Stack;

/**
 * @author liangtao
 * @Date 2019/9/19
 * 实现一个栈, 支持以下操作:
 * <p>
 * push(val) 将 val 压入栈
 * pop() 将栈顶元素弹出, 并返回这个弹出的元素
 * min() 返回栈中元素的最小值
 * 要求 O(1) 开销.
 * 保证栈中没有数字时不会调用 min()
 *
 *
 * 方法:使用两个仅支持 pop 和 push 的栈就可以完成, stack 储存压入的数据, minStack 储存最小值.使用两个仅支持 pop 和 push 的栈就可以完成,
 * stack 储存压入的数据, minStack 储存最小值.
 **/
public class Minstack {

    Stack<Integer> stack=new Stack<>();
    Stack<Integer> minstack=new Stack<>();
    public Minstack() {

    }


    public void push(int number) {
        stack.push(number);
        if (minstack.empty()){
            minstack.push(number);
        } else {
            minstack.push(Math.min(minstack.peek(),number));
        }
    }


    public int pop() {
        minstack.pop();
        return stack.pop();
    }


    public int min() {
        return minstack.peek();
    }


    public static void main(String[] args) {
        Minstack minstack = new Minstack();
        minstack.push(1);
        minstack.pop();
        minstack.push(2);
        minstack.push(3);
        minstack.min();
        minstack.push(1);
        minstack.min();
    }
}

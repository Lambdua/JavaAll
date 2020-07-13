package com.lt;

/**
 * @author liangtao
 * @Date 2019/12/4
 **/
public class MyThread extends Thread {
    private int count = 5;

    public MyThread(String name) {
        this.setName(name);
    }

    @Override
    synchronized public void run() {
        super.run();
        count--;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程名称：" + this.getName() + "  count数量：" + count);
    }


    public static void main(String[] args) {
        MyThread myThread = new MyThread("d");
        Thread thread1=new Thread(myThread,"ceshi");
        System.out.println(thread1.getName());
        System.out.println(thread1==myThread);
        Thread thread2=new Thread(myThread,"ceshi2");
        Thread thread3=new Thread(myThread,"ceshi3");
        thread1.start();
        thread2.start();
        thread3.start();

    }


}







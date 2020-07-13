package com.lt.behavioral.observerPattern.instance2;

/**
 * @author liangtao
 * @Date 2019/9/27
 **/
public class ObserverDemo {
    boolean gotoJob;
    String parent;
    String me;
    String sister;
    String brother;

    public boolean willJob() {
        parent = "去上班";
        gotoJob = true;
        return gotoJob;
    }

    public void play() {
        if (gotoJob){
            me = "go out";
            sister = "sleep";
            brother = "game";
        }

    }


    public static void main(String[] args) {
        ObserverDemo observerDemo = new ObserverDemo();
        observerDemo.willJob();
        observerDemo.play();
        System.out.println(observerDemo.sister);
    }

}

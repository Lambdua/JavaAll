package com.lt.behavioral.templatePattern.instance1;

/**
 * @author liangtao
 * @Date 2019/9/26
 **/
public abstract class Game {
    abstract void initial();

    protected abstract void doGanme();

    protected abstract void destory();

    protected abstract boolean allowPlay();

    private final void play() {
        initial();
        doGanme();
        destory();
    }


    public final void template() {
        if (allowPlay()) {
            play();
        }
    }


}

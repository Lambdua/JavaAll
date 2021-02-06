package com.lt.structural.adapt.adaptee;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public  class DlcPlayer implements AdviseMediaPlayer {
    @Override
    public void play(String fileName) {
        System.out.println("dlcplayer "+fileName);
    }
}

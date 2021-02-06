package com.lt.structural.adapt.adaptee;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public  class Mp4Player implements AdviseMediaPlayer {

    @Override
    public void play(String fileName) {
        System.out.println("mp4player "+fileName);
    }
}

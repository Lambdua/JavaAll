package com.lt.structural.adapter.client;

import com.lt.structural.adapter.target.AudioPlayer;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "beyond the horizon.mp3");
        //原来AudioPlayer是不支持下面三种模式，现在内部持有了一个adapter，可以帮助我们实现三种模式的音乐播放
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("dlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}

package com.lt.structural.adapter.instance1.client;

import com.lt.structural.adapter.instance1.target.AudioPlayer;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "beyond the horizon.mp3");
        audioPlayer.play("mp4", "alone.mp4");
        audioPlayer.play("dlc", "far far away.vlc");
        audioPlayer.play("avi", "mind me.avi");
    }
}

package com.lt.structural.adapt.adapter;

import com.lt.structural.adapt.adaptee.AdviseMediaPlayer;
import com.lt.structural.adapt.adaptee.DlcPlayer;
import com.lt.structural.adapt.adaptee.Mp4Player;
import com.lt.structural.adapt.adapter_target.MediaPlayer;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class MediaAdapter implements MediaPlayer {

    AdviseMediaPlayer adviseMediaPlayer;

    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("mp4")) {
            adviseMediaPlayer = new Mp4Player() {
            };
        } else if (audioType.equalsIgnoreCase("dlc")) {
            adviseMediaPlayer = new DlcPlayer();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        adviseMediaPlayer.play(fileName);
    }
}

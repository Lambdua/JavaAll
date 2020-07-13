package com.lt.structural.adapter.instance1.adapter;

import com.lt.structural.adapter.instance1.adaptee.AdviseMediaPlayer;
import com.lt.structural.adapter.instance1.adaptee.DlcPlayer;
import com.lt.structural.adapter.instance1.adaptee.Mp4Player;
import com.lt.structural.adapter.instance1.target.MediaPlayer;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class MediaAdapter  implements MediaPlayer {

    AdviseMediaPlayer adviseMediaPlayer;

    public MediaAdapter(String audioType){
        if (audioType.equalsIgnoreCase("mp4")){
            adviseMediaPlayer=new Mp4Player() { };
        }else if (audioType.equalsIgnoreCase("dlc")){
            adviseMediaPlayer=new DlcPlayer();
        }
    }
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("dlc")){
            adviseMediaPlayer.play(fileName);
        }else if(audioType.equalsIgnoreCase("mp4")){
            adviseMediaPlayer.play(fileName);
        }
    }
}

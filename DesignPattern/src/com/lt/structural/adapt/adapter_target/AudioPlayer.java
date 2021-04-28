package com.lt.structural.adapt.adapter_target;

import com.lt.structural.adapt.adapter.MediaAdapter;

/**
 * @author liangtao
 * @Date 2019/9/20
 **/
public class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")){
            System.out.println("audioPlayer "+fileName);
        }else if (audioType.equalsIgnoreCase("mp4")||
        audioType.equalsIgnoreCase("dlc")){
            MediaAdapter mediaAdapter=new MediaAdapter(audioType);
            mediaAdapter.play(audioType,fileName);
        }
    }
}

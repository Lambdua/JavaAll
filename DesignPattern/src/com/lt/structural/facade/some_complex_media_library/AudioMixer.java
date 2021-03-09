package com.lt.structural.facade.some_complex_media_library;

import java.io.File;

/**
 * @author liangtao
 * @description
 * @date 2021年03月09 16:15
 **/
public class AudioMixer {

    public File fix(VideoFile result) {
        System.out.println("AudioMixer: 音频转文件...");
        return new File("tmp");
    }
}

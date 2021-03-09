package com.lt.structural.facade.some_complex_media_library;

import com.lt.structural.facade.some_complex_media_library.codec.Codec;

/**
 * @author liangtao
 * @description
 * @date 2021年03月09 16:14
 **/
public class BitrateReader {
    public static VideoFile read(VideoFile file, Codec codec) {
        System.out.println("BitrateReader: 读取文件...");
        return file;
    }

    public static VideoFile convert(VideoFile buffer, Codec codec) {
        System.out.println("BitrateReader: 写文件...");
        return buffer;
    }
}

package com.lt.structural.facade;

import java.io.File;

/**
 * @author liangtao
 * @description 演示客户端
 * @date 2021年03月09 16:26
 **/
public class Client {
    public static void main(String[] args) {
        VideoConversionFacade conversionFacade = new VideoConversionFacade();
        File mp4Video = conversionFacade.convertVideo("youtubevideo.ogg", "mp4");
    }
}

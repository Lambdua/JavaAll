package com.lt.structural.proxy.third_part_media;

import lombok.ToString;

/**
 * @author liangtao
 * @description
 * @date 2021年03月10 14:48
 **/
@ToString
public class Video {
    public String id;
    public String title;
    public String data;

    Video(String id, String title) {
        this.id = id;
        this.title = title;
        this.data = "Random video.";
    }
}

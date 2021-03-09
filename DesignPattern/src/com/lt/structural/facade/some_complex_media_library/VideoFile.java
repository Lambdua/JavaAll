package com.lt.structural.facade.some_complex_media_library;

import lombok.Getter;

/**
 * @author liangtao
 * @description 视频文件
 * @date 2021年03月09 16:08
 **/
@Getter
public class VideoFile {
    private String name;

    private String codecType;

    public VideoFile(String name) {
        this.name = name;
        this.codecType = name.substring(name.indexOf(".") + 1);
    }

}

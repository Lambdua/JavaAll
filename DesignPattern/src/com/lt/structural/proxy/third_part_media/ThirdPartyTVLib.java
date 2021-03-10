package com.lt.structural.proxy.third_part_media;

import java.util.Map;

/**
 * @author liangtao
 * @description 第三方视频接口
 * @date 2021年03月10 14:47
 **/
public interface ThirdPartyTVLib {
    Map<String,Video> listVideo();

    Video getVideoInfo(String id);

    String downloadVideo(String id);

}

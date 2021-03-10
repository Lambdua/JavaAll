package com.lt.structural.proxy.third_part_media;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @description
 * @date 2021年03月10 14:59
 **/
public class TencentCacheTV implements ThirdPartyTVLib {
    private TencentTV tencentTV;

    /**
     * 内部持有一个缓存map，加快响应时间
     */
    private Map<String, Video> videoCache;

    public TencentCacheTV() {
        this.tencentTV = new TencentTV();
        this.videoCache = new HashMap<>();
    }

    @Override
    public Map<String, Video> listVideo() {
        if (videoCache.isEmpty()) {
            tencentTV.listVideo();
        }
        return videoCache;
    }

    @Override
    public Video getVideoInfo(String id) {
        if (videoCache.containsKey(id)) {
            return videoCache.get(id);
        }
        videoCache.put(id, tencentTV.getVideoInfo(id));
        return tencentTV.getVideoInfo(id);
    }

    @Override
    public String downloadVideo(String id) {
        if (videoCache.containsKey(id)) {
            return videoCache.get(id).data;
        }
        videoCache.put(id, tencentTV.getVideoInfo(id));
        return tencentTV.getVideoInfo(id).data;
    }
}

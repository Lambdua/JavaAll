package com.lt.structural.proxy.third_part_media;

import cn.hutool.core.util.RandomUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liangtao
 * @description
 * @date 2021年03月10 14:52
 **/
public class TencentTV implements ThirdPartyTVLib {
    private Map<String, Video> hMap = new HashMap<>();

    public TencentTV() {
    }

    @Override
    public Map<String,Video> listVideo() {
        experienceNetworkLatency();
        return hMap;
//        return CollUtil.newArrayList(hMap.values());
    }

    @Override
    public Video getVideoInfo(String id) {
        experienceNetworkLatency();
        if (hMap.containsKey(id)) {
            return hMap.get(id);
        }
        return null;
    }

    @Override
    public String downloadVideo(String id) {
        experienceNetworkLatency();
        if (hMap.containsKey(id)) {
            return hMap.get(id).data;
        }
        return null;
    }

    private void experienceNetworkLatency() {
        hMap.put("catzzzzzzzzz", new Video("sadgahasgdas", "Catzzzz.avi"));
        hMap.put("mkafksangasj", new Video("mkafksangasj", "Dog play with ball.mp4"));
        hMap.put("dancesvideoo", new Video("asdfas3ffasd", "Dancing video.mpq"));
        hMap.put("dlsdk5jfslaf", new Video("dlsdk5jfslaf", "Barcelona vs RealM.mov"));
        hMap.put("3sdfgsd1j333", new Video("3sdfgsd1j333", "Programing lesson#1.avi"));
        int randomLatency = RandomUtil.randomInt(5, 10);
        for (int i = 0; i < randomLatency; i++) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}

package com.lt.structural.proxy;

import com.lt.structural.proxy.third_part_media.TencentCacheTV;
import com.lt.structural.proxy.third_part_media.TencentTV;
import com.lt.structural.proxy.third_part_media.ThirdPartyTVLib;
import com.lt.structural.proxy.third_part_media.Video;

import java.util.Map;

/**
 * @author liangtao
 * @description
 * @date 2021年03月10 15:08
 **/
public class Client {

    public static void main(String[] args) {
        TencentTV tencentTV = new TencentTV();
        TencentCacheTV tencentCacheTV = new TencentCacheTV();
        System.out.println("不使用代理。。");
        test(tencentTV);

        System.out.println("使用代理。。。");
        test(tencentCacheTV);
    }

    private static void test(ThirdPartyTVLib tv) {
        System.out.println("----开始----");
        long start = System.currentTimeMillis();
        Map<String, Video> stringVideoMap = tv.listVideo();
        stringVideoMap.forEach((k, v) -> {
            tv.getVideoInfo(k);
            tv.downloadVideo(k);
        });
        System.out.println("---结束----用时： " + (System.currentTimeMillis() - start) + "ms");
    }
}

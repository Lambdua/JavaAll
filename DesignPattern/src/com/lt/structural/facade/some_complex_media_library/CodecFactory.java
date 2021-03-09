package com.lt.structural.facade.some_complex_media_library;

import com.lt.structural.facade.some_complex_media_library.codec.Codec;
import com.lt.structural.facade.some_complex_media_library.codec.MPEG4CompressionCodec;
import com.lt.structural.facade.some_complex_media_library.codec.OggCompressionCodec;

/**
 * @author liangtao
 * @description 编解码工厂
 * @date 2021年03月09 16:12
 **/
public class CodecFactory {
    /**
     * 根据video文件获取对应的编解码器
     *
     * @param file video文件
     * @return com.lt.structural.facade.some_complex_media_library.Codec
     * @author liangtao
     * @date 2021/3/9
     **/
    public static Codec extract(VideoFile file) {
        String type = file.getCodecType();
        if (type.equals("mp4")) {
            System.out.println("CodecFactory：提取mpeg音频...");
            return new MPEG4CompressionCodec();
        } else {
            System.out.println("CodecFactory：提取ogg音频...");
            return new OggCompressionCodec();
        }
    }
}

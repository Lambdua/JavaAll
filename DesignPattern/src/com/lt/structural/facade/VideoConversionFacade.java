package com.lt.structural.facade;

import com.lt.structural.facade.some_complex_media_library.AudioMixer;
import com.lt.structural.facade.some_complex_media_library.BitrateReader;
import com.lt.structural.facade.some_complex_media_library.CodecFactory;
import com.lt.structural.facade.some_complex_media_library.VideoFile;
import com.lt.structural.facade.some_complex_media_library.codec.Codec;
import com.lt.structural.facade.some_complex_media_library.codec.MPEG4CompressionCodec;
import com.lt.structural.facade.some_complex_media_library.codec.OggCompressionCodec;

import java.io.File;

/**
 * @author liangtao
 * @description 视频转换门面类, 提供了一个进行视频转换的简单接口
 * @date 2021年03月09 16:17
 **/
public class VideoConversionFacade {

    /**
     * 文件转换转换
     *
     * @param fileName 文件名
     * @param format   转换后的目标类型
     * @return java.io.File
     * @author liangtao
     * @date 2021/3/9
     **/
    public File convertVideo(String fileName, String format) {
        System.out.println("开始文件类型转换。。。");
        // 获取video文件实体对象
        VideoFile videoFile = new VideoFile(fileName);
        // 获取对应实体的资源编解码器
        Codec sourceCodec = CodecFactory.extract(videoFile);
        // 获取指定的转换编解码器
        Codec destCodec = "mp4".equalsIgnoreCase(format) ? new MPEG4CompressionCodec() : new OggCompressionCodec();
        // 使用资源编解码器 读取资源实体
        VideoFile buffer = BitrateReader.read(videoFile, sourceCodec);
        // 使用目标编解码器，转换资源实体类型
        VideoFile intermediateResult = BitrateReader.convert(buffer, destCodec);
        // 生成最终转换后的文件
        File result = new AudioMixer().fix(intermediateResult);
        System.out.println("文件类型转换结束。。");
        return result;
    }
}

package com.lt.NIO;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 梁先生
 * @Date 2020/9/9
 **/
public class NIOTest {
    @Test
    public void NIObufferTest() {
        // 分配内存大小为11的整型缓存区
        IntBuffer buffer = IntBuffer.allocate(11);
        // 往buffer里写入2个整型数据
        for (int i = 0; i < 2; ++i) {
            int randomNum = RandomUtil.randomInt();
            buffer.put(randomNum);
        }
        // 将Buffer从写模式切换到读模式
        buffer.flip();
        System.out.println("position >> " + buffer.position()
                + " limit >> " + buffer.limit()
                + " capacity >> " + buffer.capacity());
        // 读取buffer里的数据
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
        System.out.println("position >> " + buffer.position()
                + " limit >> " + buffer.limit()
                + " capacity >> " + buffer.capacity());

    }


    @Test
    public void copyTest() throws IOException {
        File file = new File("src/main/resources/NIOCopy.txt");
        try (
                //文件输入流
                FileChannel channelIn = new FileInputStream(file).getChannel();
                //文件输出流
                FileChannel channelOut = new FileOutputStream("src/main/resources/NIOCopy_copy.txt").getChannel();
        ) {
            //分配4个字节的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(4);
            //将数据从通道读入缓冲区
            while (channelIn.read(buffer) != -1) {
                //切换缓冲区的读写模式
                buffer.flip();
                //将缓冲区的数据通过通道写入到输出流中
                channelOut.write(buffer);
                //清空缓冲区，准备下一次读
                buffer.clear();
            }
        }
    }


    @Test
    public void temp() throws IOException {
        File file = new File("src/main/resources/NIOCopy.txt");

        // 打开文件输入流
        FileChannel inChannel = new FileInputStream(file).getChannel();
        // 打开文件输出流
        FileChannel outChannel = new FileOutputStream(file).getChannel();
        // 分配 1024 个字节大小的缓冲区
        ByteBuffer dsts = ByteBuffer.allocate(1024);
        // 将数据从通道读入缓冲区
        while (inChannel.read(dsts) != -1) {
            // 切换缓冲区的读写模式
            dsts.flip();
            // 将缓冲区的数据通过通道写到目的地
            outChannel.write(dsts);
            // 清空缓冲区，准备下一次读
            dsts.clear();
        }
        inChannel.close();
        outChannel.close();
    }
}


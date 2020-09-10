package com.lt.NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author 梁先生
 * @Date 2020/9/10
 **/
public class NIOClient {
    public static final int CAPACITY = 1024;

    public static void main(String[] args) throws Exception {
        ByteBuffer dsts = ByteBuffer.allocate(CAPACITY);
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6666));
        socketChannel.configureBlocking(false);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String msg = sc.next();
            dsts.put(msg.getBytes());
            dsts.flip();
            socketChannel.write(dsts);
            dsts.clear();
        }
    }
}

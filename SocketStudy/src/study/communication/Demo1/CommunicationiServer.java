package study.communication.Demo1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liangtao
 * @Date 2019/9/14
 * 通信的服务端
 * 获取一个txt文件,输出到控制台
 **/
public class CommunicationiServer {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动,等待连接");
        //创建一个ServerSocket对象,绑定端口,开始等待连接
        ServerSocket serverSocket = new ServerSocket(8888);
        //接受连接accept方法,返回socket对象
        Socket accept = serverSocket.accept();

        System.out.println("获取到连接");
        //通过socket获取输入流
        InputStream is = accept.getInputStream();
        byte bytes[] = new byte[1024];
        BufferedInputStream fis=new BufferedInputStream(is);
        int len = fis.read(bytes);
        while (len>0){
            String msg=new String(bytes,0,len,"UTF-8");
            System.out.println(msg);
            len=fis.read(bytes) ;
        }


        System.out.println("-------------------------------");

        //数据回写
        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("数据接受完毕".getBytes());
        outputStream.close();

        fis.close();
        accept.close();
//        serverSocket.close();
    }
}

package study.communication.demo2;

import java.io.*;
import java.net.Socket;

/**
 * @author liangtao
 * @Date 2019/9/14
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        //获取本地磁盘文件
       FileInputStream fis=new FileInputStream(new File("E:\\StudyAll\\SocketStudy\\src\\study\\demo2\\欠税公告接口文档.pdf"));
        //创建Socket对象
        System.out.println("客户端启动");
        Socket socket=new Socket("localhost",9999);
        //获取输出流
        OutputStream os = socket.getOutputStream();

        //输出文件
        byte data[]=new byte[1024];
        int len = fis.read(data,0,data.length);
        while (len>0){
            os.write(data,0,len);
            len=fis.read(data,0,data.length);
        }
        System.out.println("文件传输完毕");

        //资源释放
        fis.close();
        os.close();
        socket.close();

    }
}

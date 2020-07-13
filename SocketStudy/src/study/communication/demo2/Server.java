package study.communication.demo2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liangtao
 * @Date 2019/9/14
 *
 * 文件上传下载案例
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        //设置文件输出流
        FileOutputStream fos=new FileOutputStream("F:\\job\\烟台银行税务外部数据接入\\text.pdf");

        //获取serverSocket
        System.out.println("服务端启动");
        ServerSocket ss=new ServerSocket(9999);

        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        byte data[]=new byte[1024];
        int len = is.read(data, 0, data.length);
        while (len>0){
           fos.write(data,0,len);
           len=is.read(data,0,data.length);
        }

        //释放资源
        fos.close();
        is.close();
        socket.close();
    }
}

package study.communication.Demo1;


import java.io.*;
import java.net.Socket;

/**
 * @author liangtao
 * @Date 2019/9/14
 * 通信的客户端
 **/
public class CommunicationClient {
    public static void main(String[] args) throws IOException {
        System.out.println("客户端发送数据");
        Socket socket = new Socket("127.0.0.1", 8888);
        FileInputStream fis = new FileInputStream(new File("E:\\StudyAll\\SocketStudy\\src\\study\\Demo1\\测试文本.txt"));
        OutputStream outputStream = socket.getOutputStream();
        byte data[] = new byte[512];
        int len=fis.read(data);
        while ( len !=-1 ){
            outputStream.write(data);
            len = fis.read(data);
        }
        System.out.println("发送完毕");


        //读取返回数据
        InputStream is = socket.getInputStream();
        is.read(data);
        outputStream.close();
        System.out.println(new String(data));


        fis.close();
        socket.close();
    }
}

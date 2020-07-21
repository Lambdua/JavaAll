package com.lt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author liangtao
 * @Date 2020/7/21
 **/
public class SocketConnect {
    private static int webCadPort = 3181;
    private static String ip = "127.0.0.1";
    private static Socket client;
    private static char finisFlag = 0x0;


    static {
        try {
            client = new Socket(ip, webCadPort);
            if (!client.isConnected()) {
                client.close();
                System.out.println("连接失败，请检查IP或者端口是否正确，服务是否启动");
            }
        } catch (IOException e) {
            System.out.println("创建socket client失败");
            e.printStackTrace();
        }

    }

    public static Socket getClient() {
        return client;
    }

    public static void write(String jsonData) throws IOException {
        // 获取json任务数据，并发送任务。字节流要用utf8编码并用0x0结束。
        Writer writer = new OutputStreamWriter(client.getOutputStream());
        try  {
            writer.write(jsonData);
            // ！！！写一个结束标识，一起发送给服务器。
            writer.write(finisFlag);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static String respGet() {
        // 等待并读取服务器返回数据
        int nLen;
        StringBuffer strbuf = new StringBuffer();
        byte bbuf[] = new byte[1024];
        try (InputStream is = client.getInputStream()) {
            if ((nLen = is.read(bbuf)) != -1) {
                byte btemp[] = new byte[nLen];
                for (int i = 0; i < nLen; i++)
                    btemp[i] = bbuf[i];
                String resp = new String(btemp, StandardCharsets.UTF_8);
                strbuf.append(resp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strbuf.toString();
    }

    public static void close() {
        if (!client.isClosed()) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

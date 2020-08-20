package com.lt.winHaoChenCad;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author liangtao
 * @Date 2020/8/6
 **/
public class ThumbGet {
    static final String thumbPath = "E:\\home\\file\\thumb";
    //    static final String dwgPath = "D:\\WebCAD\\file\\dxf";
    static final String ocfPath = "E:\\home\\file\\ocf";
    static int webCadPort = 3181;
    static String ip = "127.0.0.1";
    public static void main(String[] args) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "1290938728981843969.ocf");
        map.put("id", new Random().nextInt());
        map.put("thumb", "324343.jpg");
        map.put("width", 400);
        map.put("height", 400);
        map.put("taskclass","GetThumb");
        String inputStr = JSONObject.toJSONString(map);

        Map<String, Object> out = new HashMap<>();
        out.put("status", 0);
        out.put("host", "WebCAD");
        out.put("input", inputStr);
        out.put("timeout",2000);
        String fin = JSON.toJSONString(out);
        System.out.println(fin);

        try (        Socket client=new Socket(ip,webCadPort);
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))
        ){
            writer.write(fin);
            writer.write(0x0);
            writer.flush();
            final String s = reader.readLine();
            System.out.println(s);
        }
    }
}

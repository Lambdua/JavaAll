package com.lt.winHaoChenCad;

import com.alibaba.fastjson.JSON;
import com.lt.util.JsonUtil;
import lombok.Data;

import java.io.*;
import java.net.Socket;
import java.util.Random;

/**
 * @author liangtao
 * @Date 2020/7/23
 **/
@Data
public class ThumbProperties {
    /**
     * 执行转化任务的名称
     * 转ocf:必须是 MakeOcf
     * 转thumb:是 GetThumb
     */
    private String taskclass="GetThumb";

    /**
     * 图纸名，不支持中文。(测试中文可以)
     */
    private String name;

    /**
     * 图纸目录名称，不能写绝对路径，不支持中文。比如在WebCADManager.exe里面设置的文件位置是C:\test,这里path赋值为1001，
     * 那么test.dwg图纸应在C:\test\dwg\1001文件夹里面，其中dwg文件夹是强制规定的，必须有。
     */
    private String path;

    /**
     * 快照文件名，同path
     */
    private String thumb;


    /**
     * 快照宽高，若=0，则使用默认宽高
     */
    private int width;
    private int height;


    /**
     * 任务编号，自定义
     */
    private Integer id;

    public static void main(String[] args) throws IOException {
         String ocfPath = "D:\\WebCAD\\file\\ocf";
        File dwgFile = new File(ocfPath);
        File[] files = dwgFile.listFiles();
        for (File file : files) {
            Socket client = new Socket(Testmain.ip, Testmain.webCadPort);
            ThumbProperties thumb=new ThumbProperties();
            String name = file.getName();
            thumb.setName(name.substring(0,name.length()-3)+"ocf");
            Random random=new Random();
            thumb.setId(random.nextInt());
            thumb.setThumb(name.substring(0, name.lastIndexOf("."))+".thumb");
            try (
                    InputStreamReader isr = new InputStreamReader(client.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))
            ) {
                String jsondata = JsonUtil.makeJson(thumb);
                writer.write(jsondata);
                writer.write(0x0);
                writer.flush();
                StringBuilder sb=new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }
                System.out.println(JSON.toJSONString(sb.toString()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}

package com.lt;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class testmain {
    private static final String dwgPath = "D:\\WebCAD\\file\\dwg";
    private static final String ocfPath = "D:\\WebCAD\\file\\ocf";

    public static void main(String[] args) {
        File dwgFile = new File(dwgPath);
        File[] files = dwgFile.listFiles();
        Random random = new Random();
        Arrays.stream(files).forEach(file -> {
            String name = file.getName();
            long mbSize = FileSizeUnit.BYTE.toMB(file.length());
            System.out.print("文件名：" + name + "; 文件大小:" + mbSize + "mb ");
            CADProperties cadProperties = new CADProperties();
            int id = random.nextInt();
            cadProperties.setId(id);
            cadProperties.setName(name);

            cadProperties.setOcf(name.substring(0,name.lastIndexOf("."))+ id + ".ocf");
            long start = System.currentTimeMillis();
            try {
                SocketConnect.write(JsonUtil.makeJson(cadProperties));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(SocketConnect.respGet());
            long end = System.currentTimeMillis();
            System.out.println("用时： " + (end - start) / 1000F + "秒");
        });

    }
}

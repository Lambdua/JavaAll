package com.lt.winHaoChenCad;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lt.util.FileSizeUnit;
import com.lt.util.JsonUtil;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Testmain {
    static final String dwgPath = "D:\\WebCAD\\file\\dwg";
//    static final String dwgPath = "D:\\WebCAD\\file\\dxf";
    static final String ocfPath = "D:\\WebCAD\\file\\ocf";

    public static void main(String[] args) throws InterruptedException {
        Testmain entity = new Testmain();
        entity.SyncSub();
    }

    static int webCadPort = 3181;
    static String ip = "127.0.0.1";
    //    private static String ip = "121.199.44.208";
    CountDownLatch countDownLatch;

    public void SyncSub() throws InterruptedException {
        long start = System.currentTimeMillis();
        File dwgFile = new File(dwgPath);
        File[] files = dwgFile.listFiles();
        int length = files.length;
        countDownLatch = new CountDownLatch(length);
        Arrays.stream(files).forEach(file -> {
            //并发执行
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new SocketThread(file)).start();
            //顺序执行
//            new SocketThread(file).run();
        });
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("总用时： " + (end - start) / 1000 + "秒");
    }


    public class SocketThread implements Runnable {
        private Socket client;
        private File file;

        public SocketThread(File file) {
            this.file = file;
        }

        @Override
        public void run() {
            try {
                client = new Socket(ip, webCadPort);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Random random = new Random();
            String name = file.getName();
            long mbSize = FileSizeUnit.BYTE.toKB(file.length());
            System.out.println("文件名：" + name + "; 文件大小:" + mbSize + "kb ");
            CADProperties cadProperties = new CADProperties();
            int id = random.nextInt();
            cadProperties.setId(id);
            cadProperties.setName(name);
//            cadProperties.setLayout("*Paper_Space");
//            cadProperties.setLayout("*Paper_Space0");
            String ocf = name.substring(0, name.lastIndexOf(".")) + ".ocf";
            cadProperties.setOcf(ocf);
            long start = System.currentTimeMillis();
            try (
                    InputStreamReader isr = new InputStreamReader(client.getInputStream());
                    BufferedReader reader = new BufferedReader(isr);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))
            ) {
                String jsondata = JsonUtil.makeJson(cadProperties);
                docomm(reader, bw, jsondata);
            } catch (IOException e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            float concumerTime = (end - start) / 1000F;
            System.out.println("用时： " + concumerTime + "秒");
            File ocfFile = new File(ocfPath + File.separator + ocf);
            System.out.println("转换后文件大小： " + FileSizeUnit.BYTE.toKB(ocfFile.length()) + "kb ");
            countDownLatch.countDown();
        }


        public JSONObject docomm(BufferedReader reader, BufferedWriter writer, String jsondata) throws IOException {
            writer.write(jsondata);
            writer.write(0x0);
            writer.flush();
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
            System.out.println(JSON.toJSONString(sb.toString()));
            return null;
        }
    }
}

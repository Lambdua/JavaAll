package com.agile.mp4.controller;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/api/v1/resource/file/")
public class OssStreamConvertController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(OssStreamConvertController.class);
//    @Resource
//    private ContainerProperties containerProperties;
//
//    @Autowired
//    @Qualifier("paasOssTool")
//    private IOssOperation paasOssTool;
//    @Autowired
//    @Qualifier("minIoOssTool")
//    private IOssOperation minIoOssTool;
//
//    @Resource
//    private OssFileRecordService ossFileRecordService;

    private static final String FILE_PATH = "C:\\Users\\liangtao\\Desktop\\11";

    /**
     * 对象存储中转请求链接-根据文件名字请求对象存储的文件流
     *
     * @param fileName 文件名称
     */
    @GetMapping("video/{fileName}")
    public void videoPlayer(@PathVariable(value = "fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File(FILE_PATH + File.separator + fileName);
        ///对文件执行分块
        fileChunkDownload(file.getAbsolutePath(), request, response);
    }

    /**
     * 文件支持分块下载和断点续传
     *
     * @param filePath 文件完整路径
     * @param request  请求
     * @param response 响应
     */
    private void fileChunkDownload(String filePath, HttpServletRequest request, HttpServletResponse response) {
        String range = request.getHeader("Range");
        System.out.println("current request rang:" + range);
        File file = new File(filePath);
        //开始下载位置
        long startByte = 0;
        //结束下载位置
        long endByte = file.length() - 1;

        //有range的话
        if (range != null && range.contains("bytes=") && range.contains("-")) {
            range = range.substring(range.lastIndexOf("=") + 1).trim();
            String[] ranges = range.split("-");
            try {
                //判断range的类型
                if (ranges.length == 1) {
                    //类型一：bytes=-2343
                    if (range.startsWith("-")) {
                        endByte = Long.parseLong(ranges[0]);
                    }
                    //类型二：bytes=2343-
                    else if (range.endsWith("-")) {
                        startByte = Long.parseLong(ranges[0]);
                        endByte = Math.min(endByte, startByte+1024 * 1024 * 10);
                    }
                }
                //类型三：bytes=22-2343
                else if (ranges.length == 2) {
                    startByte = Long.parseLong(ranges[0]);
                    endByte = Long.parseLong(ranges[1]);
                }
            } catch (NumberFormatException e) {
                startByte = 0;
                endByte = file.length() - 1;
                System.out.println("Range Occur Error,Message:" + e.getLocalizedMessage());
            }
        }
        System.out.println(String.format("文件开始位置：%s，文件结束位置：%s，文件总长度：%s", startByte, endByte, file.length()));

        //要下载的长度
        long contentLength = endByte - startByte + 1;
        System.out.println("contentLength = " + contentLength);
        //文件名
        String fileName = file.getName();
        //文件类型
        String contentType = request.getServletContext().getMimeType(fileName);

//        解决下载文件时文件名乱码问题
        byte[] fileNameBytes = fileName.getBytes(StandardCharsets.UTF_8);
        fileName = new String(fileNameBytes, 0, fileNameBytes.length, StandardCharsets.ISO_8859_1);

        //各种响应头设置
        //支持断点续传，获取部分字节内容：
        response.setHeader("Accept-Ranges", "bytes");
        //http状态码要为206：表示获取部分内容
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        response.setContentType(contentType);
        response.setHeader("Content-Type", contentType);
        //inline表示浏览器直接使用，attachment表示下载，fileName表示下载的文件名
        response.setHeader("Content-Disposition", "inline;filename=" + fileName);
        response.setHeader("Content-Length", String.valueOf(contentLength));
        // Content-Range，格式为：[要下载的开始位置]-[结束位置]/[文件总大小]
        response.setHeader("Content-Range", "bytes " + startByte + "-" + endByte + "/" + file.length());
        System.out.println(" 返回的rnge : bytes " + startByte + "-" + endByte + "/" + file.length());

        BufferedOutputStream outputStream = null;
        RandomAccessFile randomAccessFile = null;
        //已传送数据大小
        long transmitted = 0;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[4096];
            int len = 0;
            randomAccessFile.seek(startByte);
            //warning：判断是否到了最后不足4096（buff的length）个byte这个逻辑（(transmitted + len) <= contentLength）要放前面
            //不然会会先读取randomAccessFile，造成后面读取位置出错;
            while ((transmitted + len) <= contentLength && (len = randomAccessFile.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
                transmitted += len;
            }
            //处理不足buff.length部分
            if (transmitted < contentLength) {
                len = randomAccessFile.read(buff, 0, (int) (contentLength - transmitted));
                outputStream.write(buff, 0, len);
                transmitted += len;
            }

            outputStream.flush();
            response.flushBuffer();
            randomAccessFile.close();
            System.out.println("下载完毕：" + startByte + "-" + endByte + "：" + transmitted);
        } catch (ClientAbortException e) {
            System.out.println("用户停止下载：" + startByte + "-" + endByte + "：" + transmitted);
            //捕获此异常表示拥护停止下载
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("用户下载IO异常，Message：" + e.getLocalizedMessage());
        } finally {
            try {
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }///end try
    }

}

package com.lt.asposeCAD;

import com.aspose.cad.Color;
import com.aspose.cad.Image;
import com.aspose.cad.ImageOptionsBase;
import com.aspose.cad.fileformats.cad.CadDrawTypeMode;
import com.aspose.cad.imageoptions.CadRasterizationOptions;
import com.aspose.cad.imageoptions.PdfOptions;
import com.lt.util.ExcelUtil;
import com.lt.util.FileSizeUnit;

import java.io.*;
import java.util.Arrays;

/**
 * @author liangtao
 * @Date 2020/7/8
 **/
public class CADUtils {

    public static void CADtoPDF(String filePath,String outPath) {
        //1. Load the CAD file into an instance of Image.
        Image image=Image.load(filePath);
        //2. Create an instance of CadRasterizationOptions and set its mandatory properties such as PageWidth & PageHeight.
        CadRasterizationOptions options=new CadRasterizationOptions();
        //options属性详情：https://docs.aspose.com/display/cadjava/Converting+CAD+Drawings+to+PDF+and+Raster+Image+Formats
        options.setPageHeight(image.getHeight());
        options.setPageWidth(image.getWidth());
        options.setNoScaling(true);
        //设置布局名称 Gets or sets the layoutName.
        //默认就是Model布局
//        options.setLayouts(new String[]{"Model","Lauout1"});
        //设置背景色
        options.setBackgroundColor(Color.getBlack());
        //设置位置
        options.setPdfProductLocation("center");
        //设置自动布局缩放
        options.setAutomaticLayoutsScaling(true);
        options.setDrawType(CadDrawTypeMode.UseObjectColor);

        //3.Create an instance of ImageOptionsBase and set its VectorRasterizationOptions property to the instance of
        // CadRasterizationOptions created in the previous step.
        ImageOptionsBase pdfOptions=new PdfOptions(); //实现类
        pdfOptions.setVectorRasterizationOptions(options);
        File outputFile = new File(outPath);
        try(OutputStream stream=new FileOutputStream(outputFile)) {
            image.save(stream, pdfOptions);
            image.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        String filePath="D:\\WebCAD\\file\\dwg";
        String outputPath="D:\\WebCAD\\file\\pdf";
        File fileAll=new File(filePath);
        ExcelUtil.rowNum=200;
        Arrays.stream(fileAll.listFiles()).forEach(file->{
            String name = file.getName();
            long mbSize = FileSizeUnit.BYTE.toKB(file.length());
            String fileOutputPath=outputPath+File.separator+name.substring(0,name.lastIndexOf("."))+".pdf";
            System.out.print("文件名：" + name + "; 文件大小:" + mbSize + "kb ");
            long start = System.currentTimeMillis();
                CADtoPDF(file.getPath(),fileOutputPath);
            long end=System.currentTimeMillis();
            float concumerTime = (end - start) / 1000F;
            System.out.println("用时： " + concumerTime + "秒");
            File ocfFile = new File(fileOutputPath);
            System.out.println("转换后文件大小： " + FileSizeUnit.BYTE.toKB(ocfFile.length()) + "kb ");
            ExcelUtil.write2Excel(mbSize,concumerTime,FileSizeUnit.BYTE.toKB(ocfFile.length()));
        });
    }
}

package com.lt.util;

/**
 * @author liangtao
 * @Date 2020/7/22
 **/

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;


public class ExcelUtil {

    public static Workbook getWorkBook(File file) {
        String fileName = file.getName();
        Workbook workbook = null;
        try {
            FileInputStream is = new FileInputStream(file);
            if (fileName.endsWith("xls") || fileName.endsWith("XLS")) {
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("XLSX") || fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public static int rowNum = 0;

    public static void write2Excel(long fileSize, float consumerTime, long transferSize) {
        File file = new File("E:\\转换数据测试.xlsx");
        Workbook workBook = ExcelUtil.getWorkBook(file);
        Sheet sheet = workBook.getSheetAt(0);
        Row sheetRow = sheet.createRow(rowNum++);
        sheetRow.createCell(0).setCellValue(fileSize);
        sheetRow.createCell(1).setCellValue(consumerTime);
        sheetRow.createCell(2).setCellValue(transferSize);
        try (OutputStream stream = new FileOutputStream(file)) {
            workBook.write(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

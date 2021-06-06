package com.lt;

import com.aspose.cells.License;
import com.aspose.cells.Workbook;

import java.io.*;

/**
 * @author liangtao
 * @description
 * @Date 2021/6/4
 **/
public class UseAsposeCellTest {

    public static void main(String[] args) throws FileNotFoundException, NoSuchFieldException, IllegalAccessException {
        //这里需要new一下，调用静态代码块
        new License();
        ClassLoader classLoader = UseAsposeCellTest.class.getClassLoader();
        String parentPath = classLoader.getResource("").getPath();
        try (
                InputStream is = classLoader.getResourceAsStream("12M.xls");
                FileOutputStream out = new FileOutputStream(parentPath + "xls12M.pdf");
        ) {
            cells2Pdf(is, out);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static boolean cells2Pdf(InputStream in, OutputStream out) {
        try {
            Workbook workbook = new Workbook(in);
            com.aspose.cells.PdfSaveOptions pdfSaveOptions = new com.aspose.cells.PdfSaveOptions();
            pdfSaveOptions.setAllColumnsInOnePagePerSheet(true);
            workbook.save(out, pdfSaveOptions);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}

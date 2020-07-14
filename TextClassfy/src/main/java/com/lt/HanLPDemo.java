package com.lt;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.io.*;

/**
 * @author liangtao
 * @Date 2020/7/14
 **/
public class HanLPDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(new File("E:\\codeRepository\\JavaAll\\TextClassfy\\toutiao_cat_data\\toutiao_cat_data.txt")), "UTF-8"));
        OutputStreamWriter writerStream = new OutputStreamWriter(
                new FileOutputStream("E:\\codeRepository\\JavaAll\\TextClassfy\\toutiao_cat_data\\toutiao_cat_data_word.txt"), "UTF-8");
        BufferedWriter writer = new BufferedWriter(writerStream);
        String line = null;
        long startTime = System.currentTimeMillis();
        while ((line = bufferedReader.readLine()) != null) {
            String[] array = line.split("_!_");
            StringBuilder stringBuilder = new StringBuilder();
            for (Term term : HanLP.segment(array[3])) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(" ");
                }
                stringBuilder.append(term.word.trim());
            }
            writer.write(Integer.parseInt(array[1].trim()) + "_!_" + stringBuilder.toString() + "\n");
        }
        writer.flush();
        writer.close();
        System.out.println(System.currentTimeMillis() - startTime);
        bufferedReader.close();
    }
}

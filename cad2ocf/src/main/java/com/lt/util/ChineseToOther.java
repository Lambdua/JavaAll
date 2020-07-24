package com.lt.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.util.Arrays;

/**
 * @author liangtao
 * @Date 2020/7/22
 **/
public class ChineseToOther {
    public static String getPinYin(String str) {
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
        // 默认小写
        outputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 不显示拼音的声调
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // outputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        StringBuilder sb = new StringBuilder();
        try {
            for (char c : str.toCharArray()) {
                // 如果包含有中文标点除号，需要使用正则表达式
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    // if (c > 128) {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(c,
                            outputFormat)[0]);
                } else {
                    sb.append(Character.toString(c));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    /**
     * 提取每个汉字的首字母
     *
     * @param str
     * @return String
     */
    public static String getPinYinFirstChar(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
            if (pinyinArray != null) {
                sb.append(pinyinArray[0].charAt(0));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    /**
     * 将汉字转 Unicode 码
     *
     * @param str
     * @return String
     */
    public static String toUnicode(String str) {

        return StringEscapeUtils.escapeJava(str);
    }


    public static String toChinese(String str){
        return StringEscapeUtils.unescapeJava(str);

    }

    public static void main(String[] args){
        File filePath=new File("C:\\Users\\liangtao\\Downloads\\某凌铁大桥全套建筑施工图纸\\第四册图纸");
        String newFilePath="D:\\WebCAD\\file\\dwg";
        File[] files = filePath.listFiles();
        Arrays.stream(files).forEach(file -> {
            String pinYin = getPinYin(file.getName());
            file.renameTo(new File(newFilePath+File.separator+pinYin));
        });
    }
}

package com.lt.sort;

/**
 * @author liangtao
 * @description 排序测试用例
 * @date 2021年01月20 13:22
 **/
public class Test {
    public static void main(String[] args) {
        //A E L M O P R S T X u
//        Select<String> instance =new Select<>();
        Insert<String> instance=new Insert<>();
//        Shell<String> instance=new Shell<>();
//        MergeInPlace<String> instance=new MergeInPlace<>();
        instance.testCorrectness(new String[]{"S", "O", "R", "T", "u", "X", "A", "M", "P", "L", "E"});
    }
}

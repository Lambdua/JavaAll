package com.lt.slides;

import javassist.*;

import java.io.IOException;

/**
 * @author liangtao
 * @description 破解aspose说明
 * @Date 2021/6/4
 **/
public class CrackAsposeSlidesTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        String jarPath = "E:\\ownCode\\JavaAll\\aspose-crack-javassist-test\\lib\\aspose-slides-21.5-jdk16.jar";
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(jarPath);
        //加载License
        CtClass cc_public = pool.get("com.aspose.slides.internal.oa.public");

        //修改a(String,String,boolean,boolean)方法返回true,这里我是担心有别的地方使用这个核心校验函数，直接修改返回true
        CtClass[] aParams = new CtClass[1];
        aParams[0]=pool.get("java.util.Date");
//        aParams[1]=pool.get("java.lang.String");
//        aParams[2]=CtClass.booleanType;
//        aParams[3]=CtClass.booleanType;
        CtMethod public_do_method = cc_public.getDeclaredMethod("do", aParams);
        public_do_method.setBody("{ }");
        CtMethod public_if_method = cc_public.getDeclaredMethod("if", aParams);
        public_if_method.setBody("{}");

        CtClass[] doParams=new CtClass[3];
        doParams[0]=pool.get("org.w3c.dom.Node");
        doParams[1]=pool.get("org.w3c.dom.Node");
        doParams[2]=pool.get("java.lang.String[]");
        CtMethod signagureMethod = cc_public.getDeclaredMethod("do", doParams);
        signagureMethod.setBody("{}");


        //添加了一个静态代码块，直接调用a()方法，来实例化，不用每次读取xml信息
//        CtConstructor classInitializer = cc_public.getClassInitializer();
//        classInitializer.setBody("new com.aspose.cells.License().a((org.w3c.dom.Document)null);");

        //写出License.class文件
        cc_public.writeFile("C:\\Users\\liangtao\\Downloads\\a破解");
    }
}

package com.lt.cells;

import javassist.*;

import java.io.IOException;

/**
 * @author liangtao
 * @description 破解aspose说明
 * @Date 2021/6/4
 **/
public class CrackAsposeTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException {
        String jarPath = "F:\\jobCode\\dm\\platform\\AsposePojieUseTest\\lib\\aspose-cells-21.5.jar";
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath(jarPath);
        //加载License
        CtClass cc_License = pool.get("com.aspose.cells.License");

        //修改a(String,String,boolean,boolean)方法返回true,这里我是担心有别的地方使用这个核心校验函数，直接修改返回true
        CtClass[] aParams = new CtClass[4];
        aParams[0]=pool.get("java.lang.String");
        aParams[1]=pool.get("java.lang.String");
        aParams[2]=CtClass.booleanType;
        aParams[3]=CtClass.booleanType;
        CtMethod aVerifyMethod = cc_License.getDeclaredMethod("a", aParams);
        aVerifyMethod.setBody("{ return true;}");

        //修改m(String)方法，这里貌似可以去掉，当时追踪源码时加的，不记得为什么了。
        CtClass[] kmParams=new CtClass[1];
        kmParams[0]=pool.get("java.lang.String");
        CtMethod kMethod = cc_License.getDeclaredMethod("m", kmParams);
        kMethod.setBody("{ return true;}");

        //当前版本的错误信息不在显示，而是内部持有了，新增了一个方法，来获取验证错误的原因
        String method="    public void showB(){\n" +
                "        System.out.println(b);\n" +
                "    }";
        CtMethod ctMethod = CtMethod.make(method, cc_License);
        cc_License.addMethod(ctMethod);


        //这也是一个校验函数，返回true,防止别的地方调用
        CtClass[] kParams=new CtClass[1];
        kParams[0]=pool.get("java.lang.String");
        CtMethod kMethod2 = cc_License.getDeclaredMethod("k", kmParams);
        kMethod.setBody("{ return true;}");

        //核心，重写a方法，我们跳过所有验证，直接写死一下xml里面的参数，最后进行内部引用实例化已经abjs.a()
        CtClass[] aCoreParams=new CtClass[1];
        aCoreParams[0]=pool.get("org.w3c.dom.Document");
        CtMethod acoreMethod = cc_License.getDeclaredMethod("a", aCoreParams);
        acoreMethod.setBody("{\n" +
                "        this.c = new String[1];\n" +
                "        this.c[0] = \"Aspose.Cells for Java\";\n" +
                "        this.d = 2;\n" +
                "        this.e = \"8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7\";\n" +
                "        this.f = \"2199-12-3\";\n" +
                "        this.g = \"2199-12-12\";\n" +
                "        this.h = null;\n" +
                "        if (k(this.e)) {\n" +
                "            a(this.c);\n" +
                "            System.out.println(\"证书校验成功\");\n" +
                "            a = this;\n" +
                "            com.aspose.cells.zbjs.a();\n" +
                "            return;\n" +
                "        }\n" +
                "    }");


        //添加了一个静态代码块，直接调用a()方法，来实例化，不用每次读取xml信息
        CtConstructor classInitializer = cc_License.getClassInitializer();
        classInitializer.setBody("new com.aspose.cells.License().a((org.w3c.dom.Document)null);");

        //写出License.class文件
        cc_License.writeFile("F:\\jobCode\\dm\\platform\\AsposePojieUseTest\\lib");
    }
}

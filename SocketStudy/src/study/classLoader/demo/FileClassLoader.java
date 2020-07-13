package study.classLoader.demo;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.DriverManager;

/**
 * @author liangtao
 * @Date 2019/9/15
 **/
public class FileClassLoader extends ClassLoader{

    private String dirRoot;

    public FileClassLoader(String dirRoot) {
        this.dirRoot = dirRoot;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte classData[]=getClassData(name);
        if (classData==null){
            throw new ClassNotFoundException();
        }else {
            Class<?> result = defineClass(name, classData, 0, classData.length);
            return result;
        }
    }

    private byte[] getClassData(String className) {
       //读取类文件的字节
        String path=classNameToPath(className);

        try {
            InputStream is=new FileInputStream(path);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            int bufferSize=4096;
            byte classData[]=new byte[bufferSize];
            int len=0;
            while ((len=is.read(classData))!=-1){
                baos.write(classData,0,len);
            }

            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * TODO 
     * 类文件的完全路径
     * @Param [className]
     * @return java.lang.String
     **/
    private String classNameToPath(String className){
        return dirRoot+ File.separatorChar+className.replace('.',File.separatorChar)
                +".class";
    }

    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
        String dirRoot="E:\\Test\\";
        FileClassLoader fcl=new FileClassLoader(dirRoot);

        Class<?> aClass = fcl.loadClass("study.classLoader.demo.Test");
        try {
            System.out.println(aClass.newInstance().toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        File file=new File(dirRoot);
       URI uri= file.toURI();
        URL url = uri.toURL();

    }
}

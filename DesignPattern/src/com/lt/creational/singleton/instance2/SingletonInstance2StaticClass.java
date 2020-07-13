package com.lt.creational.singleton.instance2;

/**
 * @author liangtao
 * @Date 2019/9/19
 **/
public class SingletonInstance2StaticClass {
    private static class  InnerClass{
      private static final SingletonInstance2StaticClass instance=new SingletonInstance2StaticClass();

      public SingletonInstance2StaticClass getInstance(){
          return instance;
      }
    }


    private SingletonInstance2StaticClass(){};

    public static SingletonInstance2StaticClass getInstance(){
        return InnerClass.instance;
    }




    public static void main(String[] args) {
        SingletonInstance2StaticClass instance = SingletonInstance2StaticClass.getInstance();
        SingletonInstance2StaticClass instance2 = SingletonInstance2StaticClass.getInstance();

        System.out.println(instance==instance2);
    }
}

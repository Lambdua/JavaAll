package study.demo;

/**
 * @author liangtao
 * @Date 2019/10/18
 **/
public class B extends A {


    @Override
    public  void m2(){
        System.out.println("B2");
    }

    public static void main(String[] args) {
        B b = new B();
        b.m1();
    }
}

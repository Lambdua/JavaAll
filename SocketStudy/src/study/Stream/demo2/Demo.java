package study.Stream.demo2;

/**
 * @author liangtao
 * @Date 2019/9/14
 **/
public class Demo {
    public static void print(Printable print){
        print.printToUpperCase("hellp");
    }

    public static void main(String[] args) {
       print(a-> System.out.println(a.toUpperCase()));
    }
}

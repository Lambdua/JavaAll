package study.Stream.demo1;

import java.util.List;

/**
 * @author liangtao
 * @Date 2019/9/14
 **/
public class Filter {
    public static void main(String[] args) {
        List<String> list = ListUtils.createList();

        list.stream().filter(s->!s.startsWith("a")).forEach(System.out::println);
        System.out.println("----------------");
        list.stream().map(s->{
            if(s.trim().equals("12")){
                return 1222;
            }
            return s;
        }).forEach(System.out::println);
    }

}

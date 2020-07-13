package study.annotation.dbSqlCreate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liangtao
 * @Date 2019/9/14
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    String name() default "";
    //数据库表为char,需要设置长度
    int value() default 20;

    //嵌套注解
    Constraint constraint() default @Constraint;
}

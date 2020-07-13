package study.annotation.dbSqlCreate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liangtao
 * @Date 2019/9/14
 * 整型的Sql列注解
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLInteger {
   String name() default "";//列名
   Constraint constraint() default @Constraint;//嵌套注解
}

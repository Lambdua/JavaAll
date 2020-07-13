package study.annotation.dbSqlCreate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liangtao
 * @Date 2019/9/14
 * 数据库表中的字段属性显示
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraint {

    boolean isPrimaryKey() default false;
    boolean allowNull() default false;
    boolean unique() default false;
}

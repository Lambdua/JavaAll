package study.annotation.dbSqlCreate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author liangtao
 * @Date 2019/9/14
 * 根据注解生成建表语句
 **/
public class TableCreate {


    public static String create(Class clazz){
        //非法校验全部省略

        StringBuilder sb=new StringBuilder();

        //1. 获取表名
        DBTable  tableAnnotation= (DBTable) clazz.getAnnotation(DBTable.class);
        String tableName = tableAnnotation.name();
        if (tableName==""){
            //没有设置则直接受用类名为表名
            tableName=clazz.getSimpleName();
        }

        sb.append("create table "+tableName+" ( ");

        //获取列名
        int length=0;
        String name="";
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class)&&field.isAnnotationPresent(SQLString.class)){
                    //表明改类为String类型字段
                    SQLString sqlString= field.getAnnotation(SQLString.class);
                    name=sqlString.name();
                    if (name==""){
                        name=field.getName();
                    }
                    length=sqlString.value();
                sb.append(name+" varchar("+length+") ");
                if (sqlString.constraint().isPrimaryKey()) {
                    sb.append("primary key ");
                }else{
                    if (!sqlString.constraint().allowNull()){
                        sb.append(" not null ");
                    }if (sqlString.constraint().unique()){
                        sb.append(" unique ");
                    }
                }
                sb.append(", ");
            }else if ((field.getType().equals(Integer.class)||field.getType().equals(int.class))&&field.isAnnotationPresent(SQLInteger.class)){
                SQLInteger sqlInteger = field.getAnnotation(SQLInteger.class);
                 name = sqlInteger.name();
                 if (name==""){
                     name=field.getName();
                 }
                 sb.append(name +" int ");
                if (sqlInteger.constraint().isPrimaryKey()) {
                    sb.append("primary key ");
                }else{
                    if (!sqlInteger.constraint().allowNull()){
                        sb.append(" not null ");
                    }if (sqlInteger.constraint().unique()){
                        sb.append(" unique ");
                    }
                }
                sb.append(", ");

            }


        }
        sb.append(")");
        sb.replace(sb.lastIndexOf(",")-1,sb.lastIndexOf(",")+1,"");
        System.out.println(sb);
        return sb.toString();
    }


    public static void main(String[] args) {
        String s = create(TableEntity.class);

    }
}

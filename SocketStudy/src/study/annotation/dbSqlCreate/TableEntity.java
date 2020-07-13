package study.annotation.dbSqlCreate;

/**
 * @author liangtao
 * @Date 2019/9/14
 **/
@DBTable(name = "tableEntity")
public class TableEntity {
    //主键
    @SQLInteger(name = "id",constraint = @Constraint(isPrimaryKey = true))
    Integer id;

    @SQLString(name = "name",value = 20,constraint = @Constraint(allowNull = false,unique = true))
    String name;

    @SQLString(name = "age",value = 20)
    String age;

    @SQLString
    String defaultvalue;

}

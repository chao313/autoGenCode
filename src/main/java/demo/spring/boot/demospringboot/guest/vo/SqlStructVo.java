package demo.spring.boot.demospringboot.guest.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SqlStructVo {
    //                                        | Field                      | Type                | Null | Key | Default | Extra |    des
    String tableCatalog;                //   | TABLE_CATALOG              | varchar(512)        | NO   |     |         |       |    永远都是(def)
    String tableSchema;                 //   | TABLE_SCHEMA               | varchar(64)         | NO   |     |         |       |    table所在数据库
    String tableName;                   //   | TABLE_NAME                 | varchar(64)         | NO   |     |         |       |    table名称
    String columnName;                  //   | COLUMN_NAME                | varchar(64)         | NO   |     |         |       |    列名
    Integer ordinalPosition;            //   | ORDINAL_POSITION           | bigint(21) unsigned | NO   |     | 0       |       |    字段编号(1,2..)
    String columnDefault;               //   | COLUMN_DEFAULT             | longtext            | YES  |     | NULL    |       |    字段默认值
    String isNullable;                  //   | IS_NULLABLE                | varchar(3)          | NO   |     |         |       |    字段是否可以为null
    String dataType;                    //   | DATA_TYPE                  | varchar(64)         | NO   |     |         |       |    数据类型
    Integer characterMaximumLength;    //   | CHARACTER_MAXIMUM_LENGTH   | bigint(21) unsigned | YES  |     | NULL    |       |    最大字符数，不适应int.. 为null
    Integer characterOctetLength;      //   | CHARACTER_OCTET_LENGTH     | bigint(21) unsigned | YES  |     | NULL    |       |    最大字节数，和字符数有比例关系
    Integer numericPrecision;           //   | NUMERIC_PRECISION          | bigint(21) unsigned | YES  |     | NULL    |       |    数字精度，适用于数字类型int... 非数字型为null
    Integer numericScale;               //   | NUMERIC_SCALE              | bigint(21) unsigned | YES  |     | NULL    |       |    小数位数，   适用于数字类型int... 非数字型为null
    Integer datetimePrecision;          //   | DATETIME_PRECISION         | bigint(21) unsigned | YES  |     | NULL    |       |
    String characterSetName;           //   | CHARACTER_SET_NAME         | varchar(32)         | YES  |     | NULL    |       |    字符集名称(utf-8)
    String collationName;               //   | COLLATION_NAME             | varchar(32)         | YES  |     | NULL    |       |    字符集排序规则(utf8_general_ci)
    String columnType;                  //   | COLUMN_TYPE                | longtext            | NO   |     | NULL    |       |    字段类型(varchar(30))
    String columnKey;                   //   | COLUMN_KEY                 | varchar(3)          | NO   |     |         |       |    索引类型（可包含的值有PRI，代表主键，UNI，代表唯一键，MUL，可重复）
    String extra;                        //   | EXTRA                      | varchar(30)         | NO   |     |         |       |    其他信息(auto_increment)
    String privileges;                   //   | PRIVILEGES                 | varchar(80)         | NO   |     |         |       |    权限(select,insert,update,references)
    String columnComment;               //   | COLUMN_COMMENT             | varchar(1024)       | NO   |     |         |       |    字段注释
    String generationExpression;        //   | GENERATION_EXPRESSION      | longtext            | NO   |     | NULL    |       |    字段组合
}

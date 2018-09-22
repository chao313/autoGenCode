package demo.spring.boot.demospringboot.guest.vo;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * 2018/9/22    Created by   chao
 *+----------+-------------+------+-----+---------+----------------+
 *| Field    | Type        | Null | Key | Default | Extra          |
 *+----------+-------------+------+-----+---------+----------------+
 *| id       | int(10)     | NO   | PRI | NULL    | auto_increment |
 *| name     | varchar(10) | YES  |     | NULL    |                |
 *| password | varchar(10) | YES  |     | NULL    |                |
 *+----------+-------------+------+-----+---------+----------------+
 */
@Data
@ToString
public class FieldVo {
    String Field;
    String Type;
    String Null;
    String Key;
    String Default;
    String Extra;
}

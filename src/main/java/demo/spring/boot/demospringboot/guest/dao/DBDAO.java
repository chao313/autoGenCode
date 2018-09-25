package demo.spring.boot.demospringboot.guest.dao;

import demo.spring.boot.demospringboot.guest.vo.SqlStructVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import demo.spring.boot.demospringboot.guest.vo.FieldVo;

/**
 * 2018/9/22    Created by   chao
 * 目的是查询数据库的相关信息
 */
public interface DBDAO {
    /**
     * 根据database获取tables
     *
     * @return
     */
    List<String> queryTablesByDatabase();

    List<FieldVo> getFieldVosByTableName(@Param(value = "tableName") String tableName);

    String test();

    List<SqlStructVo> getSqlStructVosAll();

    List<SqlStructVo> getSqlStructVosByTableName(@Param(value = "tableName") String tableName);
}

package demo.spring.boot.demospringboot.guest.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 2018/9/22    Created by   chao
 * 目的是查询数据库的相关信息
 */
public interface DBDAO {
    /**
     * 根据database获取tables
     * @param database
     * @return
     */
    List<String> queryTablesByDatabase(@Param(value = "database") String database);

    String test();
}

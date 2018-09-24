package demo.spring.boot.demospringboot.guest.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

import demo.spring.boot.demospringboot.guest.vo.FieldVo;

/**
 * 2018/9/22    Created by   chao
 * 处理DB相关
 */
public interface DBService {

     void init() throws Exception;
    /**
     * 根据数据名称获取所有的tables
     * @return
     */
    List<String> getTables();

    List<FieldVo> getFieldsByTableName(String tableName);

    String test();


}

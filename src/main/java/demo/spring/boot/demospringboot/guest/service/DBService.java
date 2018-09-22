package demo.spring.boot.demospringboot.guest.service;

import java.util.List;

/**
 * 2018/9/22    Created by   chao
 * 处理DB相关
 */
public interface DBService {
    /**
     * 根据数据名称获取所有的tables
     * @param database
     * @return
     */
    List<String> getTablesByDataBase(String database);



}

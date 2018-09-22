package demo.spring.boot.demospringboot.guest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.guest.dao.DBDAO;
import demo.spring.boot.demospringboot.guest.service.DBInitService;
import demo.spring.boot.demospringboot.guest.service.DBService;

/**
 * 2018/9/23    Created by   chao
 */
@Service
public class DBServiceImpl implements DBService {
    @Autowired
    private DBDAO dbDAO;


//    private DBInitService dbInitService;

//    public DBServiceImpl() {
//        this.dbDAO = dbInitService.getSqlSessionFactoryBean().;
//    }



    @Override
    public List<String> getTablesByDataBase(String database) {
        return dbDAO.queryTablesByDatabase(database);
    }
}

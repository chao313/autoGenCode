package demo.spring.boot.demospringboot.guest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

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
    private DBInitService dbInitService;


    private DBDAO dbDAO;





    @Override
    public List<String> getTablesByDataBase(String database) throws Exception {
        this.dbDAO = dbInitService.getGuestSqlSessionFactoryBean().getObject().openSession().getMapper(DBDAO.class);
        return dbDAO.queryTablesByDatabase(database);
    }

    @Override
    public String test() {
        return dbDAO.test();
    }
}

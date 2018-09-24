package demo.spring.boot.demospringboot.guest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import demo.spring.boot.demospringboot.guest.dao.DBDAO;
import demo.spring.boot.demospringboot.guest.service.DBInitService;
import demo.spring.boot.demospringboot.guest.service.DBService;
import demo.spring.boot.demospringboot.guest.vo.FieldVo;

/**
 * 2018/9/23    Created by   chao
 */
@Service
public class DBServiceImpl implements DBService {

    @Autowired
    private DBInitService dbInitService;


    private DBDAO dbDAO;

    public void init() throws Exception {
        if (null == this.dbDAO) {
            this.dbDAO = dbInitService.getGuestSqlSessionFactoryBean().getObject().openSession().getMapper(DBDAO.class);
        }
    }


    @Override
    public List<String> getTables() {

        return dbDAO.queryTablesByDatabase();
    }

    @Override
    public List<FieldVo> getFieldsByTableName(String tableName) {
        return dbDAO.getFieldVosByTableName(tableName);
    }

    @Override
    public String test() {
        return dbDAO.test();
    }
}

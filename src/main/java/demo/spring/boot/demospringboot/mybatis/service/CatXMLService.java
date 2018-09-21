package demo.spring.boot.demospringboot.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.spring.boot.demospringboot.mybatis.dao.CatXMLDao;
import demo.spring.boot.demospringboot.mybatis.vo.Cat;

@Service
public class CatXMLService {
    @Autowired
    private CatXMLDao catXMLDao;

    public Cat queryById(Integer id) {
        return catXMLDao.queryById(id);
    }


}

package demo.spring.boot.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import demo.spring.boot.demospringboot.mybatis.service.CatXMLService;
import demo.spring.boot.demospringboot.mybatis.vo.Cat;

@RestController
public class MyBatisXMLController {

    @Autowired
    private CatXMLService catXMLService;

    @GetMapping(value = "/mybatis-xml-query/{id}")
    public Cat queryById(@PathVariable(value = "id") Integer id) {

        return catXMLService.queryById(id);

    }
}

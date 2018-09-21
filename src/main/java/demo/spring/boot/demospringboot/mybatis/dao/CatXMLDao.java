package demo.spring.boot.demospringboot.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import demo.spring.boot.demospringboot.mybatis.vo.Cat;

@Mapper
public interface CatXMLDao {

    Cat queryById(@Param(value = "id") Integer id);
}

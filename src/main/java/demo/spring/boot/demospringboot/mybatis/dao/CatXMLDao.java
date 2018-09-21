package demo.spring.boot.demospringboot.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import demo.spring.boot.demospringboot.vo.Cat;

@Mapper
public interface CatXMLDao {

    Cat queryById(@Param(value = "id") Integer id);
}

package demo.spring.boot.demospringboot.mybatis.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

import demo.spring.boot.demospringboot.mybatis.vo.Cat;

/**
 * 通过注解来执行sql
 */

@Mapper
public interface CatMapper {

    @Select(value = "select * from t_cat where id = #{id}")
    Cat queryById(@Param(value = "id") Integer idParam);

    @Select(value = "select * from t_cat where name = #{name}")
    List<Cat> queryByName(@Param(value = "name") String names);

    /**
     * insert sql 语句可以返回int 这里可以直接接受
     */
    @Insert(value = "insert into t_cat (name , age) values(#{name},#{age})")
    Integer insert(Cat cat);

    /**
     * 返回插入数据的信息
     *
     * 注意,返回的数据就在参数对象里面，不需要返回Integer
     */
    @Insert(value = "insert into t_cat (name , age) values(#{name},#{age})")
    @Options(keyColumn = "id" , useGeneratedKeys = true , keyProperty = "id")
    void insertReturnId(Cat cat);


    /**
     * 返回更新成功的数量
     */
    @Update(value = "update t_cat set name=#{name},age=${age} where id = ${id}")
    Integer updateById(Cat cat);

    @Delete(value = "delete from t_cat where id = #{id}")
    Integer deleteById(@Param(value = "id") Integer id);
}

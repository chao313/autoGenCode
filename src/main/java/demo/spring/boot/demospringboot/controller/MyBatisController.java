package demo.spring.boot.demospringboot.controller;

import com.github.pagehelper.PageHelper;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import demo.spring.boot.demospringboot.mybatis.service.CatService;
import demo.spring.boot.demospringboot.mybatis.vo.Cat;

@RestController
public class MyBatisController {

    @Autowired
    private CatService catService;

    @GetMapping(value = "/query-byd/{id}")
    public Cat queryById(@PathVariable(value = "id") Integer id) {
        return catService.queryById(id);
    }

    @GetMapping(value = "/query-by-name/{name}/{pageSize}/{count}")
    public List<Cat> queryById(@PathVariable(value = "name") String name,
                               @PathVariable(value = "pageSize") Integer pageSize,
                               @PathVariable(value = "count") Integer count) {
        PageHelper.startPage(pageSize, count);
        return catService.queryByName(name);
    }

    @PostMapping(value = "/insert")
    public Integer insert(@RequestBody Cat cat) {
        return catService.insert(cat);
    }

    @PostMapping(value = "/insert-return-id")
    public Cat insertRetrunId(@RequestBody Cat cat) {
        catService.insertRetrunId(cat);

        return cat;
    }

    @PutMapping(value = "/update")
    public Integer updateById(@RequestBody Cat cat) {
        return catService.update(cat);
    }

    @DeleteMapping(value = "/delete/{id}")
    public Integer deleteById(@PathVariable(value = "id") Integer id) {
        return catService.delete(id);
    }
}

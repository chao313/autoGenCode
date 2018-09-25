package demo.spring.boot.demospringboot.guest.controller;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.guest.service.DBInitService;
import demo.spring.boot.demospringboot.guest.service.DBService;
import demo.spring.boot.demospringboot.guest.vo.DBConnectVo;
import demo.spring.boot.demospringboot.guest.vo.FieldVo;
import demo.spring.boot.demospringboot.guest.vo.SqlStructVo;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@RestController
@RequestMapping(value = "/DB")
@SessionScope
public class DBController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DBInitService dbInitService;

    @Autowired
    private DBService dbService;

    @Autowired
    private DBConnectVo dbConnectVo;


    @GetMapping("/generateGuestSqlSessionFactory")
    public Response generateGuestSqlSessionFactory(@ApiParam /*使用验证*/DBConnectVo vo) {
        logger.info("vo:{}", vo);
        BeanUtils.copyProperties(vo, dbConnectVo);
        Response<Boolean> response = new Response<>();
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            dbInitService.generateGuestSqlSessionFactory(vo.getDriver(), vo.genJdbcUrl(),
                    vo.getUserName(), vo.getPassword());
            boolean result = dbInitService.isconnect();
            dbService.init();//service赋值
            response.setContent(result);
            logger.info("【guest:DBController:generateGuestSqlSessionFactory】成功", vo);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
            logger.info("【guest:DBController:generateGuestSqlSessionFactory】失败:{}", vo, e);
        }
        return response;
    }


    @GetMapping("/get-tables")
    public Response getTables() {
        logger.info("vo:{}", dbConnectVo);
        Response<List<String>> response = new Response<>();
        List<String> tables = null;
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            tables = dbService.getTables();
            response.setContent(tables);
            logger.info("【guest:DBController:getTables】成功", tables);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
            logger.info("【guest:DBController:getTables】失败:{}", tables, e);
        }
        return response;
    }



    @GetMapping("/get-fieldVos/{tableName}")
    public Response getFieldVos(@PathVariable(value = "tableName") String tableName) {
        logger.info("vo:{}", dbConnectVo);
        Response<List<FieldVo>> response = new Response<>();
        List<FieldVo> fieldVos = null;
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            fieldVos = dbService.getFieldsByTableName(tableName);
            response.setContent(fieldVos);
            logger.info("【guest:DBController:getFieldVos】成功", fieldVos);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
            logger.info("【guest:DBController:getFieldVos】失败:{}", fieldVos, e);
        }
        return response;
    }

    @GetMapping("/get-sqlStructVos-all")
    public Response getSqlStructVosAll() {
        logger.info("vo:{}", dbConnectVo);
        Response<List<SqlStructVo>> response = new Response<>();
        List<SqlStructVo> vos = null;
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            vos = dbService.getSqlStructVosAll();
            response.setContent(vos);
            logger.info("【guest:DBController:getSqlStructVosAll】成功", vos);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
            logger.info("【guest:DBController:getSqlStructVosAll】失败:{}", vos, e);
        }
        return response;
    }

    @GetMapping("/get-sqlStructVos/{tableName}")
    public Response getSqlStructVosByTableName(@PathVariable(value = "tableName") String tableName) {
        logger.info("vo:{}", dbConnectVo);
        Response<List<SqlStructVo>> response = new Response<>();
        List<SqlStructVo> vos = null;
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            vos = dbService.getSqlStructVosByTableName(tableName);
            response.setContent(vos);
            logger.info("【guest:DBController:getSqlStructVosByTableName】成功", vos);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
            logger.info("【guest:DBController:getSqlStructVosByTableName】失败:{}", vos, e);
        }
        return response;
    }


    @GetMapping("/test")
    public Response test() {
        Response<String> response = new Response<>();
        String result = null;
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            result = dbService.test();
            response.setContent(result);
            logger.info("【guest:DBController:test】成功", result);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
            logger.info("【guest:DBController:test】失败:{}", result, e);
        }
        return response;
    }


}

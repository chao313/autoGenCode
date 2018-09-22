package demo.spring.boot.demospringboot.guest.controller;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.guest.service.DBInitService;
import demo.spring.boot.demospringboot.guest.service.DBService;
import demo.spring.boot.demospringboot.guest.vo.DBConnectVo;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@RestController
@RequestMapping(value="/DB")
@SessionScope
public class DBController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DBInitService dbInitService;

    @Autowired
    private DBService dbService;

    @Autowired
    private DBConnectVo dbConnectVo;


    @GetMapping("/connect")
    public Response Connect(@ApiParam /*使用验证*/DBConnectVo vo) {
        logger.info("vo:{}", vo);
        BeanUtils.copyProperties(vo, dbConnectVo);
        Response<Boolean> response = new Response<>();
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            dbInitService.connect(vo.getDriver(), vo.genJdbcUrl(),
                    vo.getUserName(), vo.getPassword());
            boolean result = dbInitService.isconnect();
            response.setContent(result);
            logger.info("【guest:DBController:Connect】成功", vo);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
            logger.info("【guest:DBController:Connect】失败:{}", vo, e);
        }
        return response;
    }


    @GetMapping("/get-tables")
    public Response getTablesByDataBase() {
        logger.info("vo:{}", dbConnectVo);
        Response<List<String>> response = new Response<>();
        List<String> tables = null;
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            tables = dbService.getTablesByDataBase(dbConnectVo.getDatabase());
            response.setContent(tables);
            logger.info("【guest:DBController:Connect】成功", tables);
        } catch (Exception e) {
            response.setCode(Code.System.FAIL);
            response.setMsg(e.toString());
            response.addException(e);
            logger.info("【guest:DBController:Connect】失败:{}", tables, e);
        }
        return response;
    }


}

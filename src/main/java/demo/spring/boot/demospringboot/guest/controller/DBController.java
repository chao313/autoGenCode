package demo.spring.boot.demospringboot.guest.controller;

import demo.spring.boot.demospringboot.framework.Code;
import demo.spring.boot.demospringboot.framework.Response;
import demo.spring.boot.demospringboot.guest.service.DBService;
import demo.spring.boot.demospringboot.guest.vo.DBVo;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/DB")
public class DBController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DBService dbService;


    @GetMapping("/connect")
    public Response Connect(@ApiParam /*使用验证*/DBVo vo) {
        logger.info("vo:{}", vo);
        Response<Boolean> response = new Response<>();
        try {
            response.setCode(Code.System.OK);
            response.setMsg(Code.System.SERVER_SUCCESS_MSG);
            dbService.connect(vo.getDriver(), vo.genJdbcUrl(),
                    vo.getUserName(), vo.getPassword());
            boolean result = dbService.isconnect();
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
}

package demo.spring.boot.demospringboot.controller.guest;

import demo.spring.boot.demospringboot.db.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBController {

    @Autowired
    private DBService dbService;



    public Connect(){

    }
}

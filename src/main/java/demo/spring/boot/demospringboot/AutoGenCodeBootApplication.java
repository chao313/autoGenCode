package demo.spring.boot.demospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import demo.spring.boot.demospringboot.util.SpringContextUtil;

@SpringBootApplication
public class AutoGenCodeBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(AutoGenCodeBootApplication.class, args);

        SpringContextUtil.setApplicationContext(app);
    }
}

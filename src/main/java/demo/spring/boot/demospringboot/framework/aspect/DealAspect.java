package demo.spring.boot.demospringboot.framework.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DealAspect {
    protected final Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * Pointcut 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数 该方法就是一个标识，不进行调用
     */
    @Pointcut(value = "@annotation(demo.spring.boot.demospringboot.framework.custom.annotation.Deal)")
    private void deal() {
    }

    @Before("deal()")
    private void dealBefore (){

    }

}

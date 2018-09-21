package demo.spring.boot.demospringboot.framework.custom.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Deal {
    String value() default "";
}

package wxt.spring.framework.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WAutowired {
    String value() default "";
}

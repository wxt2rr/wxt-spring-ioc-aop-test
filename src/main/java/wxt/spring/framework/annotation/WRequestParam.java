package wxt.spring.framework.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WRequestParam {
    String value() default "";
}

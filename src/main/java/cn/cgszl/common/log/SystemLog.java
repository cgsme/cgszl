package cn.cgszl.common.log;

import java.lang.annotation.*;

/**
 * 自定义注解
 *
 * @author cguisheng 2018/4/5 16:14
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    // 操作模块
    String module() default "";

    // 方法
    String methods() default "";
}

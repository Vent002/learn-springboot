package cn.hsmxg1204.core.cache.aop;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-17 15:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface MyCacheable {
    String key();
    //秒 为单位
    long expire() default -1;
}

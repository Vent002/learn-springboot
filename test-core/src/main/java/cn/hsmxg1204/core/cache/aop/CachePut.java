package cn.hsmxg1204.core.cache.aop;

import java.lang.annotation.*;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-17 16:08
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CachePut {
    String key();
    //秒 为单位 默认缓存为 是30分钟
    long expire() default 30*60;
}

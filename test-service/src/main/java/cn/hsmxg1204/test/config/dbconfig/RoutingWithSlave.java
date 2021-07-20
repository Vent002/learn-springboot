package cn.hsmxg1204.test.config.dbconfig;



import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-12 16:52
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface RoutingWithSlave {
}

package cn.hsmxg1204.test.config.dbconfig;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-12 16:09
 */
@Aspect
@Component
public class RoutingAspect {
    @Around("@annotation(routingWithSlave)")
    public Object routingWithDataCource(ProceedingJoinPoint joinPoint,RoutingWithSlave routingWithSlave) throws Throwable {
        try(RoutingDataSourceContext ctx = new RoutingDataSourceContext(RoutingDataSourceContext.SLAVE_DATASOURCE)){
            return joinPoint.proceed();
        }
    }
}

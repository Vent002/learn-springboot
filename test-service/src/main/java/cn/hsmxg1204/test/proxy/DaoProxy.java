package cn.hsmxg1204.test.proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-08-28 21:59
 */
public class DaoProxy implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("do "+method.getName() +" start ,StartTime=["+System.currentTimeMillis()+"]");
        method.invoke(o,objects);
        System.out.println("do "+method.getName() +" start ,EndTime=["+System.currentTimeMillis()+"]");
        return o;
    }
}

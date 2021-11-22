package cn.hsmxg1204.test.utils;

import cn.hsmxg1204.test.proxy.DaoProxy;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-08-28 22:04
 */
@Component
public class ProxyUtils {

    public static Enhancer doCheckForDao(Class clz){
        DaoProxy daoProxy = new DaoProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clz);
        enhancer.setCallback(daoProxy);
        return enhancer;
    }
}

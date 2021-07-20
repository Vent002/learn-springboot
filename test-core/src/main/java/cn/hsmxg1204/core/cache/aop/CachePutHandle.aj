package cn.hsmxg1204.core.cache.aop;


import cn.hsmxg1204.core.exception.MyException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * TODO
 *
 * @description
 * @author gxming
 * @date 2021-04-17 16:09
 */
public aspect CachePutHandle {
    @Autowired
    MyCacheConfig myCacheConfig;
    @Autowired
    MyCache myCache;

    private final String REPLACE_NULL ="THIS is NULL";

    @Around("@annotation(cache)")
    public Object aroundMethod(ProceedingJoinPoint point,CachePut cachePut){
        String key;
        Object value = null;
        String key1 = cachePut.key();
        if(key1.contains("#")){
            //获取真正的值
            key = myCacheConfig.generateKeyBySpEL(key1,point);
        }else{
            key = cachePut.key();
        }
        try {
            value = point.proceed();
            if(value == null) value = REPLACE_NULL;
            myCache.put(key,value,cachePut.expire());
            if(REPLACE_NULL.equals(value)){
                value = null;
            }
        }  catch (Throwable throwable) {
            throw new MyException(throwable.getMessage());
        }
        return value;
    }
}

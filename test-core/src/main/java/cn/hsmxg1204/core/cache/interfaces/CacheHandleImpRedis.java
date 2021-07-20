package cn.hsmxg1204.core.cache.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-15 13:49
 */
@Component
@ConditionalOnProperty(
        value = {"spring.cache.type"},
        havingValue = "redis"
)
public class CacheHandleImpRedis implements MyCache{
    @Autowired
    RedisTemplate redisTemplate;
    public CacheHandleImpRedis(){
    }
    @Override
    public Object get(String str) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object obj = valueOperations.get(str);
        return obj;
    }

    @Override
    public void put(String str1, Object obj, long var) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(str1,obj, var,TimeUnit.SECONDS);
    }

    @Override
    public boolean delete(String str) {
        Boolean isOk = redisTemplate.delete(str);
        return isOk;
    }
}

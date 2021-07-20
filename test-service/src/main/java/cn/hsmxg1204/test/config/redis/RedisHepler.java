package cn.hsmxg1204.test.config.redis;

import cn.hsmxg1204.core.exception.MyException;
import cn.hsmxg1204.core.utils.SyncCommandCallback;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-04-15 9:41
 */
@Component
@Slf4j
public class RedisHepler {

    @Autowired
    RedisClient redisClient;

    private GenericObjectPool<StatefulRedisConnection<String, String>> redisConnectionPool;

    @PostConstruct
    public void init() {
        GenericObjectPoolConfig<StatefulRedisConnection<String, String>> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxIdle(5);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        this.redisConnectionPool = ConnectionPoolSupport.createGenericObjectPool(() -> redisClient.connect(),
                poolConfig);
    }

    @PreDestroy
    public void shutdown() {
        this.redisConnectionPool.close();
        this.redisClient.shutdown();
    }


    public  <T> T executeSync(SyncCommandCallback<T> callback){
        try(StatefulRedisConnection<String,String> connection = redisConnectionPool.borrowObject()){
            connection.setAutoFlushCommands(true);
            RedisCommands<String,String> commands = connection.sync();
            return callback.doInConnection(commands);
        }catch (Exception e){
            log.warn("executeSync redis failed {}",e);
            throw new MyException(e);
        }
    }

    /**
     * type string
     * 添加值到redis
     * @param key key
     * @param value 值
     * @return
     */
    public String set(String key,String value){
        return executeSync(commands -> commands.set(key,value));
    }

    /**
     * type String
     * 添加值并设置过期时间
     * @param key key
     * @param expireTime 过期时间 类型：long，单位：秒
     * @param value 值
     * @return
     */
    public String settex(String key,long expireTime,String value){
        return executeSync(commands -> commands.setex(key,expireTime,value));
    }

    /**
     * type string
     * 查询对应键值
     * @param key key
     * @return
     */
    public String get(String key){
        return executeSync(commands -> commands.get(key));
    }

    /**
     * type hash
     * 给key中的 field 键赋值为value
     * @param key key
     * @param field field
     * @param value 值
     * @return
     */
    public boolean hset(String key, String field, String value) {
        return executeSync(commands -> commands.hset(key, field, value));
    }

    /**
     * type hash
     * 从key取出field的value
     * @param key key
     * @param field field
     * @return
     */
    public String hget(String key, String field) {
        return executeSync(commands -> commands.hget(key, field));
    }

    /**
     * type hash
     *  获取所有key的所有值信息
     * @param key key
     * @return
     */
    public Map<String, String> hgetall(String key) {
        return executeSync(commands -> commands.hgetall(key));
    }

}

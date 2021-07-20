package cn.hsmxg1204.test.config.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-12 19:16
 */
@Data
@ConfigurationProperties("spring.redis")
public class RedisConfiguration {
    private String host;
    private int port;
    private String password;
    private int database;

    @Bean
    RedisClient redisClient(){
        RedisURI uri = RedisURI.builder().redis(this.host,this.port)
                .withPassword(this.password)
                .withDatabase(this.database)
                .build();
        return RedisClient.create(uri);
    }
}

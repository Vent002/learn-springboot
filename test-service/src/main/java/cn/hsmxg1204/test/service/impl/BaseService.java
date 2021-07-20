package cn.hsmxg1204.test.service.impl;

import cn.hsmxg1204.common.service.vilad.Validators;
import cn.hsmxg1204.test.config.redis.RedisHepler;
import cn.hsmxg1204.test.dao.UserDao;
import cn.hsmxg1204.test.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * TODO
 *
 * @author gxming
 * @description
 * @date 2021-07-13 15:55
 */
public class BaseService {
    @Autowired
    protected RedisHepler redisService;
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected IUserService iUserService;

    @Autowired
    protected StringRedisTemplate redisTemplate;

    @Autowired
    protected Validators validators;

    @Autowired
    protected UserDao userService;
}
